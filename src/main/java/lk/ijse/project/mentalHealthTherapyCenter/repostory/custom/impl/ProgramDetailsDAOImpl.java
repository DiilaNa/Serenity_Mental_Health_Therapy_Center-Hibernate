package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.entity.ProgramDetails;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.ProgramDetailsDAO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgramDetailsDAOImpl implements ProgramDetailsDAO {
    @Override
    public boolean save(ProgramDetails programDetails, Session session)  {
        try{
            session.persist(programDetails);
            session.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
          throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(ProgramDetails programDetails, Session session) {
        try{
            session.merge(programDetails);
            session.flush();
            return true;
        }catch(Exception e){
           throw new RuntimeException();
        }
    }

    @Override
    public List<ProgramDetails> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<ProgramDetails> findByPK(String pk, Session session)  {
        ProgramDetails programDetails = session.get(ProgramDetails.class, pk);
        session.close();
        if (programDetails == null) {
            return Optional.empty();
        }
        return Optional.of(programDetails);
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }
}
