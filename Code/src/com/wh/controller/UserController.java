package com.wh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.dto.Children;
import com.wh.dto.PcResult;
import com.wh.dto.ResponseResult;
import com.wh.dto.UserTree;
import com.wh.model.T01;
import com.wh.model.T02;
import com.wh.model.T06;
import com.wh.service.JobService;
import com.wh.service.UserService;

/***
 * 该控制层用于处理一些用户的增删改查操作
 *  
 * @author ZXC
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	JobService jobService;
	

	/**
	 * 
	 * @功能描述: 查询所有用户 根据f0205查询
	 * @提交方式: get
	 * @时间:2017年5月23日 下午10:07:58
	 * @方法名: findUsers 
	 * @接口地址: http://120.76.145.236:8090/WH/user/findEnterpriseUsers?f0205=企业    该链接返回所有企业用户
	 * 			http://120.76.145.236:8090/WH/user/findEnterpriseUsers?f0205=万汇   该链接返回所有万汇的用户
	 * 			http://120.76.145.236:8090/WH/user/findEnterpriseUsers?f0205=政府   该链接返回所有的政府用户
	 * @测试数据: 
	 * @param f0205 用户类别，是一个固定值，分别为：万汇，企业，政府
	 * @return
	 * @return ResponseResult<List<T02>>
	 */
	@RequestMapping(value="/findEnterpriseUsers", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T02>> findUsers(String f0205){
		ResponseResult<List<T02>> rr;
		if(f0205 == null){
			rr = new ResponseResult<List<T02>>(false,"未查询到用户");
		}else{
			List<T02> list = userService.findEnterpriseUsers(f0205);
			if(!list.isEmpty()){
				rr = new ResponseResult<List<T02>>(true,list);
			}else{
				rr = new ResponseResult<List<T02>>(false,"未查询到用户");
			}
		}
		return rr;
	}
		
	/**
	 * 
	 * @功能描述: 通过用户名和密码登录
	 * 			 登录成功返回该用户信息(包括登录上来的用户的个人信息以及他所属部门的信息，这里需要做一个判断,因为不能确定该用户是企业用户还是政府用户，所以需要判断返回的json字符串中data.t01或者data.t06是否为空,其中一个不为空就表示这个是他的部门信息,)
	 * 			 登录失败返回错误信息
	 * @提交方式: GET
	 * @时间:2017年5月23日 下午10:22:53
	 * @方法名: login 
	 * @接口地址: http://120.76.145.236:8090/WH/user/login?username=peiyangjun&password=123456
	 * @测试数据: {"success":true,"data":{"f0201":1,"f0202":"admin","f0203":"123456","f0204":"管理员","f0205":"wh","f0206":"是","f0207":"15213202324","f0208":"15213202324","f0209":101000001,"t01":{"f0101":101000001,"f0601":10600010,"f0102":"腾讯公司","f0103":"企业用户","f0104":"私营企业","f0105":942076800000,"f0106":"广东深圳","f0107":"深圳","f0108":"马化腾","f0109":"13301335544","f0110":"张小龙","f0111":"13301334455","f0112":"一级企业","f0113":"蓝","f0114":234.56,"f0115":453.05,"f0116":"企业工艺流程简介","f0117":"企业周边环境简介","f0118":"普通企业","f0119":"./","f0120":"企业大门照片","f0121":"已启动","f0122":"已达标","f0123":"已达标","f0124":"已编制","f0125":"互联网","f0126":"主要原辅材料","f0127":"产品、副产品及中间产物","f0128":"普通企业","f0129":"腾讯"},"t06":null},"error":null}
	 * @param username
	 * @param password
	 * @return
	 * @return ResponseResult<T02>
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T02> login(String username,String password){
	
		T02 t02 = userService.userLogin(username, password);
		//System.out.println("-----------"+t02.getF0204());
		ResponseResult<T02> rr;
		if(t02 == null){
			rr = new ResponseResult<>(false,new String("用户名或者密码错误"));
		}else{
			rr = new ResponseResult<T02>(true, t02);
		}
		return rr;
	}
	
	/**
	 * @功能描述: 该方法用于手机端，pc端的用户添加，在注册成功时返回一个boolean值和一个添加用户的的对象
	 * 			手机端需要传入至少个三参数（用户名，密码，所属企业） 
	 * 			注册不成功的判定是用户名和密码同时重复。需要提示用户修改用户名或者密码再次提交
	 * @提交方式: POST
	 * @时间:2017年5月5日 下午8:20:10
	 * @方法名: addUser 
	 * @接口地址:	http://120.76.145.236:8090/WH/user/addUser?  f0202="参数1"&f0203="参数2";
	 * @测试数据:	
	 * @param f0202 登陆帐号:建议为用户名全拼
	 * @param f0203 用户登陆密码（本系统暂时采用明文密码）
	 * @param f0204 用户的真实姓名
	 * @param f0205 用户类型：主要是说明该用户属于（硬）万汇公司、政府部门、企业用户
	 * @param f0206 管理员:(硬）：是、否，注只有是管理员的，才有权在本部门下增加、删除员工信息，万汇公司的管理员，可以增加所有信息
	 * @param f0207 联系电话1
	 * @param f0208 联系电话2
	 * @param f0209 该用户所属业，或是乡镇。如果该用户属于企业，该值为F0101,如果该用户属于政府人员，该值为F0601
	 * @return
	 * @return ResponseResult<T02>
	 */
	@RequestMapping(value="/addUser", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T02> addUser(String f0202,String f0203,String f0204,String f0205,String f0206,String f0207,String f0208,String f0209){
		T02 t = new T02();
		t.setF0202(f0202);
		t.setF0203(f0203);
		t.setF0204(f0204);
		t.setF0205(f0205);
		t.setF0206(f0206);
		t.setF0207(f0207);
		t.setF0208(f0208);
		//f0209为空表示当前用户为万汇用户
		//String ss = null;
		System.err.println("---"+f0209);
		if(f0209 != null){
			//用户F0209字段为0 表示该用户为万汇用户
			t.setF0209(Integer.parseInt(f0209));
		}
		
		ResponseResult<T02> rr;
		
		if(userService.addUser(t) == null){
			rr = new ResponseResult<T02>(false, "该用户已存在");
		}else{
			rr = new ResponseResult<T02>(true, t);
		}
		
		return rr;
	}
	
	
	/**
	 * @功能描述: 该接口用于修改用户信息
	 * @提交方式: POST
	 * @时间:2017年5月10日 下午6:54:19
	 * @方法名: updateUserInfo 
	 * @接口地址: http://120.76.145.236:8090/WH/user/updateUserInfo?f0201=102000001&f0202=peiyangjun&f0203=123456&f0204=裴仰军&f0205=万汇公司&f0206=是&f0207=15213202324&f0208=15213202323
	 * @测试数据: f0201="102000001"&f0202="peiyangjun"&f0203="123456"&f0204="裴仰军"&f0205="万汇公司"&f0206="是"&f0207="15213202324"&f0208="15213202323"
	 * @param f0201 用户id
	 * @param f0202 登录名
	 * @param f0203 密码
	 * @param f0204 真实姓名
	 * @param f0205 用户类型,主要是说明该用户属于（硬）万汇公司、政府部门、企业用户
	 * @param f0206 管理员:(硬）：是、否
	 * @param f0207 联系电话1
	 * @param f0208 联系电话2
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateUserInfo(String f0201,String f0202,String f0203,String f0204,String f0205,String f0206,String f0207,String f0208){
		ResponseResult<String> rr;
		T02 t02 = new T02(Integer.parseInt(f0201),f0202,f0203,f0204,f0205,f0206,f0207,f0208,null);
		if(userService.updateUserInfo(t02) >= 1 ){
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "修改失败，请重试");
		}
		
		return rr;
	}
	
	
	/**
	 * 
	 * @功能描述: PC端左边的菜单树,后台生成之后自动发送到前端
	 * @提交方式: 不限
	 * @时间:2017年5月23日 下午10:30:50
	 * @方法名: tree 
	 * @接口地址: http://120.76.145.236:8090/WH/user/tree
	 * @测试数据:
	 * @return
	 * @return PcResult<String>
	 */
	@RequestMapping(value="/tree",produces ="application/json;charset=UTF-8")
	@ResponseBody
	public PcResult<String> tree(){
		PcResult<String> rr;
		StringBuilder s1 = new StringBuilder("<ul class=\"submenu\"> ");
		List<T06> list = userService.selectMenuTree();
		List<T01> t01List = jobService.findAllEnterprise();
			for(T06 t1: list ){
				
				if(t1.getF0603() != 0){
					continue;
				}		
				s1.append("<li>");
				if(t1.getF0603() == 0){
					s1.append("<a href=\"#\"  onclick=\"getTreeID(this)\" class=\"dropdown-toggle\"><i class=\"icon-double-angle-right\"></i><span class=\"menu-text company\">");
					s1.append(t1.getF0602()+"</span>");
					s1.append("<input type=\"hidden\" value=\""+t1.getF0601()+"\" name=\"\">");	
					s1.append("<b class=\"arrow icon-angle-down\"></b></a>");
				}
				 
				s1.append("<ul class=\"submenu\">");
				//筛选属于这个地区的企业
				for(T01 t01 : t01List ){
					//表示该企业属于这个地区管理
					if(t01.getF0601() - t1.getF0601() == 0){
						
						s1.append("<li><a href=\"#\" onclick=\"getTreeID(this)\"><i class=\"icon-arrow-right\"></i><span class=\"menu-text company\">");
						s1.append(t01.getF0102()+"</span>");
						s1.append("<input type=\"hidden\" value=\""+t01.getF0101()+"\" name=\"\">");	
						s1.append("</a></li>");

					}
				}
				for(T06 t2 : list){
					//表示当前街道属于这个地区
					if(t2.getF0603() - t1.getF0601() == 0){
						
						s1.append("<li><a href=\"#\" onclick=\"getTreeID(this)\"  class=\"dropdown-toggle\">	<i class=\"icon-double-angle-right\"></i><span class=\"menu-text company\">");
						s1.append(t2.getF0602()+"</span>");
						s1.append("<input type=\"hidden\" value=\""+t2.getF0601()+"\" name=\"\">");	
						s1.append("	<b class=\"arrow icon-angle-down\"></b></a>");		
//						s1.append("<li><a href=\"#\" class=\"dropdown-toggle eid\"><i class=\"icon-double-angle-right\"></i><span class=\"menu-text\">"+t2.getF0602()+ "</span><input type=\"hidden\" value=\""+t2.getF0601()+"\" name=\"\"><b class=\"arrow icon-angle-down\"></b></a>");
						s1.append("<ul class=\"submenu\">");
						for(T01 t01 : t01List ){
							//表示该企业属于这个街道管理
							if(t01.getF0601() - t2.getF0601() == 0){
								s1.append("<li><a  onclick=\"getTreeID(this)\" href=\"#\"><i class=\"icon-arrow-right\"></i><span class=\"menu-text company\">");
								s1.append(t01.getF0102()+"</span>");
								s1.append("<input type=\"hidden\" value=\""+t01.getF0101()+"\" name=\"\">");
								s1.append("</a></li>");
								//s1.append("<li><a href=\"#\" class=\"dropdown-toggle eid\"><i class=\"icon-arrow-right\"></i>"+t01.getF0102()+"<input type=\"hidden\" value=\""+t01.getF0101()+"\" name=\"\"></a></li>");
							}
						}
						s1.append("</ul></li>");
					}
				}
				s1.append("</ul></li>");
			}
			s1.append("</ul>");
			
			rr = new PcResult<String>(true,"",s1.toString(),null);

		return rr;
	}

	/**
	 * @功能描述: PC端删除用户时调用的接口,根据用户的ID进行删除数据库中的数据,删除成功会返回true以及一条删除成功的提示,失败会返回false以及一条删除失败的提示
	 * @提交方式 GET
	 * @时间:2017年5月11日 上午12:00:03
	 * @方法名: removeUser 
	 * @接口地址:http://120.76.145.236:8090/WH/user/removeUser?f0201=102000002
	 * @测试数据: 
	 * @param f0201 用户ID
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/removeUser", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> removeUser(String f0201){
		ResponseResult<String> rr;
		T02 t = new T02();
		t.setF0201(Integer.parseInt(f0201));
		if(userService.deleteUser(t) >=1){
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;
	}
	
	
	/**
	 * @功能描述: 获取企业街道菜单树，type为1，会返回带有企业的菜单树
	 * 							type为2，会返回只有街道地区的菜单树
	 * @提交方式: GET
	 * @时间:2017年5月19日 下午11:13:35
	 * @方法名: userTree 
	 * @接口地址: http://120.76.145.236:8090/WH/user/userTree?type=1  
	 * 			http://120.76.145.236:8090/WH/user/userTree?type=2
	 * @测试数据:
	 * @param type 
	 * @return
	 * @return List<UserTree>
	 */
	@RequestMapping(value="/userTree", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public List<UserTree> userTree(String type){
		List<UserTree> l1 = new ArrayList<UserTree>();
		List<T06> list = userService.selectMenuTree();
		List<T01> t01List = jobService.findAllEnterprise();
		
		//企业
		if(type.equals("1")){
			for(T06 t1 : list){
				UserTree ut; 
				List<Children> chList = new ArrayList<>();
				List<Children> chList1 = new ArrayList<>();
				Children ch;
				Children ch1;
					if(t1.getF0603() != 0){
						continue;
					}else{
						ut = new UserTree();
						ut.setId(t1.getF0601());
						
						ut.setText(t1.getF0602());
						
						for(T01 t01 : t01List ){
							//表示该企业属于这个地区管理
							if(t01.getF0601() - t1.getF0601() == 0){
								ch = new Children();
								ch.setId(t01.getF0101());
								ch.setText(t01.getF0102());
								ch.setState("false");
								chList.add(ch);
							}
						}
						for(T06 t2:list){	
							//表示这个街道属于这个地区
							if(t2.getF0603() - t1.getF0601() == 0){
								ch = new Children();
								ch.setId(t2.getF0601());
								ch.setText(t2.getF0602());
											
								for(T01 t01 : t01List ){
									//表示该企业属于这个地区管理
									if(t01.getF0601() - t2.getF0601() == 0){
										ch1 = new Children();
										ch1.setId(t01.getF0101());
										ch1.setText(t01.getF0102());
										ch1.setState("false");
										chList1.add(ch1);
									}
								}
								
								ch.setChildren(chList1);
								if(ch.getChildren().isEmpty()){
									ch.setState("false");
								}else{
									ch.setState("closed");
								}
								chList.add(ch);
							}
						}
						
						ut.setChildren(chList);
						if(ut.getChildren().isEmpty()){
							ut.setState("false");
						}else{
							ut.setState("closed");
						}
						l1.add(ut);
					}
			}
		}
		//政府
		else if(type.equals("2")){
			for(T06 t1 : list){
				UserTree ut; 
				List<Children> chList = new ArrayList<>();
					if(t1.getF0603() != 0){
						continue;
					}else{
						ut = new UserTree();
						ut.setId(t1.getF0601());
						ut.setState("closed");
						ut.setText(t1.getF0602());
						
						for(T06 t2:list){
							Children ch;
							//表示这个街道属于这个地区
							if(t2.getF0603() - t1.getF0601() == 0){
								ch = new Children();
								ch.setId(t2.getF0601());
								ch.setState("false");
								ch.setText(t2.getF0602());
								chList.add(ch);
							}
						}
						
						ut.setChildren(chList);
						ut.setChildren(chList);
						if(ut.getChildren().isEmpty()){
							ut.setState("false");
						}else{
							ut.setState("closed");
						}
						l1.add(ut);
					}
			}
		}
		
		return l1;
	}
}