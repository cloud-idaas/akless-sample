package com.alibaba.idaas.sample;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.cloud_idaas.adapter.alibabacloud.pam.IDaaSPamAklessCredentialFactory;
import com.cloud_idaas.adapter.domain.DeveloperApiTypeEnum;
import com.cloud_idaas.core.factory.IDaaSCredentialProviderFactory;
import com.google.gson.Gson;

public class SampleOSS {

    // Specify the ARN of the managed Alibaba Cloud role
    static final String ALIBABA_CLOUD_OIDC_ROLE_ARN = "acs:ram::xxx";

    public static OSS createClient() throws Exception {

        // The endpoint corresponding to the region where the Bucket is located.
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

        // Get the credentialProvider from IDaaS
        CredentialsProvider credentialsProvider = IDaaSPamAklessCredentialFactory.getOSSV1CredentialProvider(ALIBABA_CLOUD_OIDC_ROLE_ARN,
                DeveloperApiTypeEnum.OBTAIN_ACCESS_CREDENTIAL);

        // Bucket name.
        String bucketName = "examplebucket";
        // Resource group ID.
        //String rsId = "rg-xxx";
        // Region where the Bucket is located.
        String region = "cn-hangzhou";
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                // Pass the credentialProvider into the client
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        return ossClient;
    }

    public static void main(String[] args_) throws Exception {
        // Read configuration file to complete IDaaS configuration initialization
        IDaaSCredentialProviderFactory.init();
        // Initialize  client
        OSS client = SampleOSS.createClient();
        System.out.println(new Gson().toJson(client.listBuckets()));
    }
}