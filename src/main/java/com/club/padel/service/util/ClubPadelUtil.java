package com.club.padel.service.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class ClubPadelUtil {
	
//	/**
//	 * @param Roles
//	 * @return 
//	 * @throws ClubPadelException
//	 */
//	public static Roles checkRoles(RolesRepository RolesRepository, int Roles_id,MessageSource mensajes) throws ClubPadelException {
//		Optional<Roles> Rolesdbexist = RolesRepository.findById(Roles_id);
//		
//		//If the athleta does not exists error because should be created previously
//		if (!Rolesdbexist.isPresent()) {
//			throw new ClubPadelException(ClubPadelUtil.getString(mensajes,"AppTrainerUtil.Roles")+ Roles_id+ClubPadelUtil.getString(mensajes,"AppTrainerUtil.notexists")); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//		
//		return Rolesdbexist.get();
//	}
//	
//	public static Exercise checkExercise(ExerciseRepository exerciseRepository, Integer exercise_id,
//			MessageSource mensajes) throws ClubPadelException {
//		Optional<Exercise> exerciseDb = exerciseRepository.findById(exercise_id);
//		
//		//If the athleta does not exists error because should be created previously
//		if (!exerciseDb.isPresent()) {
//			throw new ClubPadelException(ClubPadelUtil.getString(mensajes,"AppTrainerUtil.exercise")+ exercise_id+ClubPadelUtil.getString(mensajes,"AppTrainerUtil.notexists")); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//		
//		return exerciseDb.get();
//
//	}	
//	
//	/**
//	 * @param Roless
//	 * @param padelTraining
//	 */
//	public static void removeRoless(List<Roles> Roless, Training training) {
//		List<Roles> Rolessdb = new ArrayList<Roles>(training.getRoless());
//		for (Roles Roles: Rolessdb) {
//    		
//    		boolean encontrado = false;
//    		for (Roles Rolesrequest: Roless) {
//    			if (Rolesrequest.equals(Roles)) {
//    				encontrado=true;
//    				break;
//    			}
//    		}
//    		
//    		if (!encontrado) {
//    			training.getRoless().remove(Roles);
//    		}
//    		
//    	}
//	}
//	
//	/**
//	 * @param Roless
//	 * @param padelTraining
//	 * @throws ClubPadelException
//	 */
//	public static void addRoless(RolesRepository RolesRepository,List<Roles> Roless, Training training,MessageSource mensajes) throws ClubPadelException {
//		for (Roles Roles: Roless) {
//			ClubPadelUtil.checkRoles(RolesRepository,Roles.getId(),mensajes);
//    		
//    		boolean encontrado = false;
//    		for (Roles Rolesdb: training.getRoless()) {
//    			if (Rolesdb.equals(Roles)) {
//    				encontrado=true;
//    				break;
//    			}
//    		}
//    		
//    		if (!encontrado) {
//    			training.getRoless().add(Roles);
//    		}
//    		
//    	}
//	}
//	
//	/**
//	 * @param id
//	 * @param group
//	 * @return
//	 * @throws ClubPadelException
//	 */
//	public static Group checkGroup(GroupRepository groupRepository,Integer id,MessageSource mensajes) throws ClubPadelException {
//		Group group=null;
//		try {
//			group = groupRepository.findById(id).get();
//		} catch(NoSuchElementException ex) {
//			throw new ClubPadelException(ClubPadelUtil.getString(mensajes,"AppTrainerUtil.group")+id+ClubPadelUtil.getString(mensajes,"AppTrainerUtil.notexists")); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//		return group;
//	}
//	
//	/**
//	 * @param id
//	 * @param group
//	 * @return
//	 * @throws ClubPadelException
//	 */
//	public static Training checkTraining(TrainingRepository trainingRepository,Integer id,MessageSource mensajes) throws ClubPadelException {
//		Training training=null;
//		try {
//			training = trainingRepository.findById(id).get();
//		} catch(NoSuchElementException ex) {
//			throw new ClubPadelException(ClubPadelUtil.getString(mensajes,"AppTrainerUtil.training")+id+ClubPadelUtil.getString(mensajes,"AppTrainerUtil.notexists")); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//		return training;
//	}
//	
//	public static LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
//	    return dateToConvert.toInstant()
//	      .atZone(ZoneId.systemDefault())
//	      .toLocalDateTime();
//	}
//	
//	public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
//	    return java.sql.Timestamp.valueOf(dateToConvert);
//	}
//	
//	/**
//	 * @param training_date
//	 * @return
//	 */
//	public static Date addMonth(Date training_date) {	
//		
////		GregorianCalendar calendar = new GregorianCalendar();
////	    calendar.setTime(training_date);
////		
////	    calendar.add((GregorianCalendar.MONTH), 1);
//	    
//		LocalDateTime ldt = ClubPadelUtil.convertToLocalDateViaInstant(training_date);
////		LocalDateTime ldt_next = ldt.with(TemporalAdjusters.firstDayOfNextMonth());
//		LocalDateTime ldt_next = ldt.plusMonths(1);
//		Date newdate = ClubPadelUtil.convertToDateViaSqlTimestamp(ldt_next);
//	    
////	    Date newdate = java.util.Date.from(calendar.toZonedDateTime().toInstant());
//		return newdate;
//	}
//
	public static String getString(MessageSource mensajes,String key) {
		return mensajes.getMessage(key, null, LocaleContextHolder.getLocale());
	}

}
