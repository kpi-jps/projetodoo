package com.vettodos.model.domain.usecases.util;

import java.time.LocalDateTime;

public class ManipuladorDatas {
    public static LocalDateTime stringParaLocalDateTime(String data) {
        return LocalDateTime.now();
    }
}
