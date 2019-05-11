package ua.petproject.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Vote;
import ua.petproject.repository.user.CrudUserRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

    private static final Sort SORT_BY_USER_ID = new Sort(Sort.Direction.ASC, "user");

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    public Vote save(Vote vote) {
        if (!vote.isNew() && get(vote.getId()) == null) {
            return null;
        }
        else return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll(SORT_BY_USER_ID);
    }
}
