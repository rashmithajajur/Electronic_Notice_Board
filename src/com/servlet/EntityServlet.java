package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CategoryBean;
import com.beans.EventsBean;
import com.beans.PersonBean;
import com.beans.ProfessorBean;
import com.beans.StudentBean;
import com.dbproject.dao.CategoryDao;
import com.dbproject.dao.DepartmentDao;
import com.dbproject.dao.EventDao;
import com.dbproject.dao.LoginDao;
import com.dbproject.dao.PersonDao;
import com.dbproject.dao.StudentDao;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EntityServlet")
public class EntityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntityServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.setContentType("text/html");    
			PrintWriter out = response.getWriter();    

			String entity=request.getParameter("entity");    
			String isDelete=request.getParameter("isDelete");
			HttpSession session = request.getSession(false);  
			if(isDelete!=null && isDelete.equals("Y")){
				deleteEntity(entity, request);
				RequestDispatcher rd=request.getRequestDispatcher("thankyou.jsp");
				rd.forward(request,response);
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				if(entity!=null){
					RequestDispatcher rd=null;
					if(entity.equalsIgnoreCase("studententity")){    
						PersonBean bean = new PersonBean();
						String student = (String)request.getParameter("studentid");
						String fname = (String)request.getParameter("studentname");
						bean.setUserid(student);	
						bean.setFname(fname);
						bean.setType("STUDENT");
						PersonDao.insertPerson(bean);
						int studentId = PersonDao.fetchPersonId(student);
						String major = (String)request.getParameter("major");
						String departmentName = (String)request.getParameter("departmentName");
						int deptId=DepartmentDao.fetchIdByName(departmentName);
						StudentBean studentBean = new StudentBean();
						studentBean.setStudentId(studentId);
						studentBean.setMajor(major);
						studentBean.setDeptId(deptId);
						DepartmentDao.assignDeptToStudent(studentBean);
						rd=request.getRequestDispatcher("thankyou.jsp");
						rd.forward(request,response);    
					}else if(entity.equalsIgnoreCase("evententity")){
						EventsBean event = new EventsBean();

						String eventName = (String)request.getParameter("eventname");
						String venue = (String)request.getParameter("venue");
						String description = (String)request.getParameter("eventdescription");
						String eventDte = (String)request.getParameter("eventdate");
						//sdf.setLenient(false);
						Date eventDate = sdf.parse(eventDte);
						int totalSeats = Integer.parseInt((String)request.getParameter("totalseats"));
						double ticketPrice = Double.parseDouble((String)request.getParameter("ticketprice"));
						String category = (String)request.getParameter("category");
						String department = (String)request.getParameter("department");
						int categoryId=CategoryDao.getCategoryIdByName(category);
						String fromprof=(String)request.getParameter("fromprof");
						if(fromprof!= null && fromprof.length()>0){
							int departmentId=DepartmentDao.getDepartmentIdByName(department);
							event.setDepartmentId(departmentId);
							event.setCategoryId(0);
						}else{
							event.setDepartmentId(0);
							event.setCategoryId(categoryId);
						}

						event.setEventName(eventName);
						event.setVenue(venue);
						event.setDescription(description);
						event.setEventDate(eventDate);
						event.setTotalNoOfSeats(totalSeats);
						event.setTicketPrice(ticketPrice);
						EventDao.insertEvents(event);
						EventDao.insertAlerts(event);
						rd=request.getRequestDispatcher("thankyou.jsp");
						rd.forward(request,response);    
					}else if(entity.equalsIgnoreCase("categoryentity")){
						String catName = request.getParameter("categoryname");
						String catDescription = request.getParameter("categorydescription");
						CategoryDao.insertCategory(catName, catDescription);
						rd=request.getRequestDispatcher("thankyou.jsp");
						rd.forward(request,response);
					}else if(entity.equalsIgnoreCase("departmententity")){
						String catName = request.getParameter("departmentname");
						String catDescription = request.getParameter("deptdescription");
						DepartmentDao.insertDept(catName, catDescription);
						rd=request.getRequestDispatcher("thankyou.jsp");
						rd.forward(request,response);
					}else if(entity.equalsIgnoreCase("professorentity")){
						PersonBean bean = new PersonBean();
						String professor = (String)request.getParameter("professorid");
						String fname = (String)request.getParameter("professorname");
						bean.setUserid(professor);	
						bean.setFname(fname);
						bean.setType("PROFESSOR");
						PersonDao.insertPerson(bean);
						int professorId = PersonDao.fetchPersonId(professor);
						String subjects = (String)request.getParameter("subjects");
						String departmentName = (String)request.getParameter("department");
						int deptId=DepartmentDao.fetchIdByName(departmentName);
						ProfessorBean profBean = new ProfessorBean();
						profBean.setProfessorId(professorId);
						profBean.setSubjects(subjects);
						profBean.setDepartmentId(deptId);
						DepartmentDao.assignDeptToProfessor(profBean);
						rd=request.getRequestDispatcher("thankyou.jsp");
						rd.forward(request,response);
					}  
				}
			}
			out.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void deleteEntity(String entity, HttpServletRequest request) {
		String parameter = request.getParameter("ID");
		if(entity.equalsIgnoreCase("studententity")){
			PersonDao.deletePerson(parameter);
		}else if(entity.equalsIgnoreCase("professorentity")){
			PersonDao.deletePerson(parameter);
		}else if(entity.equalsIgnoreCase("departmententity")){
			DepartmentDao.deleteDept(parameter);
		}else if(entity.equalsIgnoreCase("categoryentity")){
			CategoryDao.deleteCategory(parameter);
		}else if(entity.equalsIgnoreCase("evententity")){
			EventDao.deletEvent(parameter);
		}
		
	}

}
