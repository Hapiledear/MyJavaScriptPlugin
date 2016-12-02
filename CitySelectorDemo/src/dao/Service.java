package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Service extends BaseDao {
	
	private Statement stmt ;
	public List<CdeBnkLink> find(String sql){
		List<CdeBnkLink> results = new ArrayList<>();
		try {
			conn = getConnection();
		//	String sql1 = "select lbnk_no,lbnk_nm from BAP.T_BAP_CDE_BNK_LINK t where lbnk_prov = '11' ";
			stmt = conn.createStatement();
		//	pstm.setString(1, prov);
			rs =stmt.executeQuery(sql);
			while(rs.next()){
				CdeBnkLink dto = new CdeBnkLink();
				dto.setLbnkNo(rs.getString("lbnk_no"));
				dto.setLbnkNm(rs.getString("lbnk_nm"));
				
				results.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	
	public List<CdeBnkLink> findByProv(String prov){
		List<CdeBnkLink> results = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select lbnk_no,lbnk_nm from BAP.T_BAP_CDE_BNK_LINK t where lbnk_prov = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, prov);
			rs = pstm.executeQuery();
			while(rs.next()){
				CdeBnkLink dto = new CdeBnkLink();
				dto.setLbnkNo(rs.getString("lbnk_no"));
				dto.setLbnkNm(rs.getString("lbnk_nm"));
				
				results.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public List<CdeBnkLink> findByCity(String code) {
		List<CdeBnkLink> results = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select lbnk_no,lbnk_nm from BAP.T_BAP_CDE_BNK_LINK t where lbnk_city = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, code);
			rs = pstm.executeQuery();
			while(rs.next()){
				CdeBnkLink dto = new CdeBnkLink();
				dto.setLbnkNo(rs.getString("lbnk_no"));
				dto.setLbnkNm(rs.getString("lbnk_nm"));
				
				results.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public List<CdeBnkLink> findByBankName(String name) {
		name = "%"+name+"%";
		List<CdeBnkLink> results = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select lbnk_no,lbnk_nm from BAP.T_BAP_CDE_BNK_LINK t where lbnk_nm like ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				CdeBnkLink dto = new CdeBnkLink();
				dto.setLbnkNo(rs.getString("lbnk_no"));
				dto.setLbnkNm(rs.getString("lbnk_nm"));
				
				results.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
}
