package com.codestates.pre_project.question.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.comment.dto.CommentResponseDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String problemContent;

    @Column(nullable = false, length = 500)
    private String expectContent;

    @Column(nullable = false)
    private int likes = 0;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_NOTSELECT;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();


    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if(!this.member.getQuestions().contains(this)){
            this.member.getQuestions().add(this);
        }
    }
    public Question(Member member){
        this.member = member;
    }


    /* question comment 임시 매핑 삭제
    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    public void setComment(Comment comment) {
        comments.add(comment);
        if (comment.getQuestion() != this) {
            comment.setQuestion(this);
        }
    }
     */

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers = new ArrayList<>();

    public void setAnswer(Answer answer){
        answers.add(answer);
        if(answer.getQuestion() != this){
            answer.setQuestion(this);
        }
    }



    public enum QuestionStatus {
        QUESTION_SELECT("채택된 답안이 있는 문의"),
        QUESTION_NOTSELECT("채택된 답안이 없는 문의"),
        QUESTION_DELETE("삭제된 질문");

        @Getter
        private String status;

        QuestionStatus(String status) {
            this.status = status;
        }
    }
}

