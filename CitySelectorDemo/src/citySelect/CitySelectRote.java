package citySelect;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.CdeBnkLink;
import dao.Service;

/**
 * Servlet implementation class CitySelectRote
 */
@WebServlet("/search")
public class CitySelectRote extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Service service = new Service();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitySelectRote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();  
		out.flush();
		int pageNum =Integer.parseInt(request.getParameter("pageNum"));
		
		System.out.println(pageNum+"");
		
		String keyWord = request.getParameter("keyWord");
		System.out.println(keyWord);
		String[] words = keyWord.split(" ");
		List<CdeBnkLink> resultList = new ArrayList<>();
	
		
		String bankNameSearch = "";
		String sql = "select lbnk_no,lbnk_nm from BAP.T_BAP_CDE_BNK_LINK t where cde_flg = '1' ";
		for(String str : words){
			int type = 	SelectCityHelper.getZoneType(str);
			String code = SelectCityHelper.getZoneCode(str);
			if(type == 2){
				sql =sql + " and lbnk_city = '"+code+"' ";
				System.out.println(sql);
				resultList = service.find(sql);
				
			}
			if(type == 1){
				sql =sql + " and lbnk_prov = '"+code+"' ";
				System.out.println(sql);
				resultList = service.find(sql);
			//	resultList = service.findByProv(code);
			}
			
			if(type == 0){
				bankNameSearch +="%"+str+"%";
				sql = sql + " and lbnk_nm like '"+bankNameSearch+"' ";
				System.out.println(sql);
				resultList = service.find(sql);
			}
		}
		
		CdeBnkLink test = new CdeBnkLink();
		test.setLbnkNm("test");
		test.setLbnkNo("test");
		if(pageNum>1){
			resultList.add(test);
		}
		
		String jsonStr = JSON.toJSONString(resultList);
		System.out.println(jsonStr);
		out.write(jsonStr);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
