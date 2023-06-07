package com.example.jl_entities.bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditClientRequest {

    private String fname;
    private String lname;
    private String email;
    private String tel;
//    private Long idType;

}
