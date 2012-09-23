package educational_assingnment.executers;

import java.io.File;
import java.util.ArrayList;

import educational_assigment.gateways.ExcelGateway;
import educational_assingment.data_base.CurriculumTransaction;
import educational_assingment.models.StoreCurriculumModels;

public class Dispatcher {
	static private StoreCurriculumModels store_models;
	static private CurriculumTransaction trans = new CurriculumTransaction();;
	
	static public void loadCurriculum(File file, String specialty, StoreCurriculumModels m){
		ArrayList<ArrayList<Object>> res = ExcelGateway.firstLoadCurriculum(file, specialty);
		m.addModel(res, specialty);
	}
	static public void deleteCurriculumData(String specialty, StoreCurriculumModels m){
		trans.deleteCurriculumData(specialty);
		m.removeModel(specialty);
	}
	static public void setStoreCurriculum(StoreCurriculumModels store){
		store_models = store;
	}
	static public StoreCurriculumModels getStoreCurriculum(){
		return store_models;
	}
	static public boolean safetyDevice(){
		ArrayList<String> spec = trans.getSpecialties();
		ArrayList<String> cath = trans.getCathedras();
		if(spec.size()==0)
			return false;
		if(cath.size()==0)
			return false;
		return true;
	}
	static public void saveChanges(StoreCurriculumModels store){
		store.saveChanges();
	}
	static public void createEducAssignment(ArrayList<String> cath,  File path, String mode){
		CurriculumTransaction tr = new CurriculumTransaction();
		EducAssigCreator asCreator = new EducAssigCreator();
		ArrayList<String> specialty = tr.getSpecialties();
		for(String cur_cath: cath){
			String cur_path = path.getPath()+"\\"+cur_cath+".xls";
			ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
			
			for(String cur_spec : specialty){
				ArrayList<ArrayList<Object>> data = tr.getCurriculumData(cur_spec);
				for(ArrayList<Object> cur_data:data){
					System.out.println(cur_data.get(6).toString()+"   "+cur_cath);
					if(cur_data.get(6).toString().compareTo(cur_cath)==0){
						//winter, summer!!!!!!!!!!!!!
						int sem = Integer.parseInt(cur_data.get(5).toString());
						if(mode.compareTo("summer")==0)
							if(sem%2==0)
								result.add(cur_data);
						if(mode.compareTo("winter")==0)
							if(sem%2!=0)
								result.add(cur_data);
						
					}
				}
			}
			asCreator.createAssignment(cur_path, result);
		}
	}
	static public boolean isAvailableCreation(){
		CurriculumTransaction tr = new CurriculumTransaction();
		ArrayList<String> specialty = tr.getSpecialties();
		boolean flag_cath = true;
		boolean flag_subgroup = true;
		for(String cur_spec : specialty){
			ArrayList<ArrayList<Object>> data = tr.getCurriculumData(cur_spec);
			if(data!=null){
				for(ArrayList<Object> cur_subject:data){
					if(cur_subject.get(5)==null)
						flag_cath = false;
					if(Integer.parseInt(cur_subject.get(1).toString())!=0){
						if(Integer.parseInt(cur_subject.get(3).toString())==0)
							flag_subgroup=false;
					}
				}
			}
		}
		if(flag_cath && flag_subgroup)
			return true;
		else
			return false;
	}
}
