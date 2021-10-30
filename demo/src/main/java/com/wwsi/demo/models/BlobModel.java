package com.wwsi.demo.models;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class BlobModel {
    String id;
    String url;
}
