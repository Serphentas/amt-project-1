package stackoverflow.application.vote;

import stackoverflow.domain.vote.IVoteRepo;

public class VoteFacade {
    private IVoteRepo voteRepo;

    public VoteFacade(IVoteRepo voteRepo){ this.voteRepo = voteRepo; }
}
