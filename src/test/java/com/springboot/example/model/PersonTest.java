package com.springboot.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@JsonTest
public class PersonTest {

    @Autowired
    private JacksonTester<Person> json;

    @Test
    public void testSerialize() throws IOException {
        final Person person = new Person("Harshad", "Ranganathan");
        assertThat(this.json.write(person)).extractingJsonPathStringValue("@.firstName").isEqualTo("Harshad");
        assertThat(this.json.write(person)).extractingJsonPathStringValue("@.lastName").isEqualTo("Ranganathan");
    }
}
