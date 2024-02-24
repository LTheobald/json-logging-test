package uk.co.ltheobald.jsonloggingtest;

import com.pulumi.Pulumi;
import com.pulumi.aws.ecr.Repository;
import com.pulumi.aws.ecr.RepositoryArgs;
import com.pulumi.aws.ecr.inputs.RepositoryImageScanningConfigurationArgs;
import com.pulumi.aws.ecs.Cluster;
import com.pulumi.aws.ecs.ClusterArgs;
import com.pulumi.aws.ecs.TaskDefinition;
import com.pulumi.aws.ecs.TaskDefinitionArgs;
import com.pulumi.core.Output;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;


public class PulumiInfrastructure {

  public static void run() {
    Pulumi.run(ctx -> {
      // ECR repo to store Docker images
      var repository = new Repository("json-logging-ecr-repository", RepositoryArgs.builder()
          .imageScanningConfiguration(RepositoryImageScanningConfigurationArgs.builder()
              .scanOnPush(true)
              .build())
          .build());

      // Create an ECS cluster
      var ecsCluster = new Cluster("json-logging-ecs-cluster", ClusterArgs.builder().build());

      // Define the execution role that the Fargate task will use
      // @TODO Does the account-id need replacing?
      String taskExecutionRoleArn = "arn:aws:iam::account-id:role/ecsTaskExecutionRole";

      JSONObject containerInfo = new JSONObject();
      containerInfo.put("name", "json-logging-test-container").put("image", "%s:latest")
          .put("portMappings", new JSONObject[]{new JSONObject().put("containerPort", 80).put("hostPort", 80)});

      // Define a Task Definition for the Fargate service
      // The container image should be in the ECR repository created earlier
      /*var taskDefinition = new TaskDefinßßition("app-task", TaskDefinitionArgs.builder()
          .family("json-logging-app")
          .cpu("256") // 256 CPU units
          .memory("512") // 512 MiB
          .networkMode("awsvpc")
          .requiresCompatibilities("FARGATE")
          .executionRoleArn(taskExecutionRoleArn)
          .containerDefinitions(Output.of(repository.repositoryUrl())
              .apply(url -> String.format(
                  "[]",
                  url))
          ).build());*/
    });
  }
}
