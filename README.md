## Description
This code repository contains examples for calling the AKless SDK of Alibaba Cloud IDaaS EIAM product.

## How to Run
```text
Configuration file is specified via JVM parameter or environment variable:
JVM parameter specification: -Dcloud_idaas_config_path
Environment variable name: CLOUD_IDAAS_CONFIG_PATH

Examples for related environment are as follows:
# Development computer environment
-Dcloud_idaas_config_path=classpath:alibaba_cloud_idaas_config_for_human.json
# Alibaba Cloud ECS environment
-Dcloud_idaas_config_path=classpath:alibaba_cloud_idaas_config_for_ecs.json
```