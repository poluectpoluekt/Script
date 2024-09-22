package com.ed;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ScriptFirstTest {

    @Test
    void getSendDate() {

            Date expectedSubmissionDate = Date.valueOf("2024-10-1");

            Date actualSendDate = ScriptFirst.getSendDate();

            assertEquals(expectedSubmissionDate, actualSendDate);
    }
}