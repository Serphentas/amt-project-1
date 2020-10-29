package stackoverflow.application.vote;

import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.IVoteRepo;
import stackoverflow.domain.vote.Vote;


public class VoteFacade {
    private IVoteRepo voteRepo;

    public VoteFacade(IVoteRepo voteRepo){
        this.voteRepo = voteRepo;
    }

    public void proposeVote(ProposeVoteCmd proposeVoteCmd){
        Vote vote = Vote.builder()
                .voteId(proposeVoteCmd.getId())
                .personId(proposeVoteCmd.getPersonId())
                .questionId(proposeVoteCmd.getQuestionId())
                //.commentId(proposeVoteCmd.getCommentId())
                .build();
        voteRepo.toggle(vote);
    }

    public boolean hasVotedQuestion(QuestionId questionId, PersonId currentUser){
        return voteRepo.hasVotedQuestion(questionId, currentUser);
    }
    public int nbrVoteQuestion(QuestionId questionId){
        return voteRepo.nbrVoteQuestion(questionId);
    }

    /*
    public boolean hasVotedByCommentId(CommentId commentId, PersonId personId){
        return voteRepo.hasVotedComment(commentId, personId);
    }

    public int nbrVoteComment(CommentId commentId){
        return voteRepo.nbdrVoteComment(CommentId commentId);
    }
    */
}