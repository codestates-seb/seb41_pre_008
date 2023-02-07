package com.codestates.pre_project.tag;

import com.codestates.pre_project.tag.entity.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagCreateTest {
    @Test
    @DisplayName("create 단위 테스트")
    public void TagCreateTest1(){
        Tag tag = new Tag();
        String name = "java";
        String content = "Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself. This tag is frequently used alongside other tags for libraries and/or frameworks used by Java developers.";
        tag.setName(name);
        tag.setContent(content);

        String expectedName = "java";
        String expectedContent = "Java is a high-level object-oriented programming language. Use this tag when you're having problems using or understanding the language itself. This tag is frequently used alongside other tags for libraries and/or frameworks used by Java developers.";

        String actualName = tag.getName();
        String actualConetent = tag.getContent();

        assertThat(actualName, is(equalTo(expectedName)));
        assertThat(actualConetent, is(equalTo(expectedContent)));
    }
}
