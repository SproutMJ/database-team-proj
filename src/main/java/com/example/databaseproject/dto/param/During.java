package com.example.databaseproject.dto.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class During {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date begin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end;
}
