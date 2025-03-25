package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;

import java.util.List;
import java.util.Optional;


public interface TProgramDAO extends CrudDAO<TPrograms,String> {

    List<TPrograms> findByIds(List<String> therapyIDs);
}
