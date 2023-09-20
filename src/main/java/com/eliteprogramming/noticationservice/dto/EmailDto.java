package com.eliteprogramming.noticationservice.dto;

import com.eliteprogramming.noticationservice.enums.FileTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
public class EmailDto implements Serializable {

    private String subject;
    private String toList;
    private String body;

}