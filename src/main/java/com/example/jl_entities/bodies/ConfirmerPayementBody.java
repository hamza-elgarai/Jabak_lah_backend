package com.example.jl_entities.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
public class ConfirmerPayementBody {
    private List<Long> impayes;
    private Long clientId;
    private String code;
}
