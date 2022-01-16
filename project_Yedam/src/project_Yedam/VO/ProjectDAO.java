package project_Yedam.VO;

import java.util.List;

public interface ProjectDAO<C, P> {

	// 전체 테이블 목록 조회
	public List<C> selectAll();
	
	public C selectOne(P p);
	
	// 테이블에 추가  
	public void insert(C c);
	
	// DB수정
	public void update(C c);
	
	// DB에서 삭제
	public void delete(C c);

}
