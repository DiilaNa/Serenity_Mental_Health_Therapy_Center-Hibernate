package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.SuperDAO;

import java.util.List;

public interface QueryDAO  extends SuperDAO {
    List<TPrograms> getALLTherapists();
}
