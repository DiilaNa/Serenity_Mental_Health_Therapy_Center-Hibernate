package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;

import java.util.Optional;


public interface TProgramDAO extends CrudDAO<TPrograms,String> {
    Optional<String> findByiD(String pk);
}
