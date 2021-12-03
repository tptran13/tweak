package edu.neiu.tweak.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig
{
    private Cloudinary cloudinary;

    //configure my cloudinary account
    @Autowired
    public CloudinaryConfig(@Value("${cloud.key}") String key,
                            @Value("${cloud.secret}") String secret,
                            @Value("${cloud.name}") String name)
    {
        this.cloudinary = Singleton.getCloudinary();
        this.cloudinary.config.cloudName = name;
        this.cloudinary.config.apiKey = key;
        this.cloudinary.config.apiSecret = secret;
    }

    //method is used to upload images
    public Map<String, Object> upload (Object file, Map<String, Object> options) throws IOException
    {
        return cloudinary.uploader().upload(file, options);
    }
}
