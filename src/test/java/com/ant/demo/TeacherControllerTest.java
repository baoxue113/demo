/**
 * 
 */
package com.ant.demo;

import com.alibaba.fastjson.JSONObject;
import com.ant.demo.controller.from.TeacherStudentFrom;
import com.ant.demo.result.Result;
import com.ant.demo.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhailiang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Resource
	private TeacherService teacherService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * 根据Teacher name查询出Teacher及所有Student
	 *
	 * @throws Exception
	 */
	@Test
	public void queryTeacherAndStudentByName() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String reuslt = mockMvc.perform(
				get("/teacher/get").param("name", "张曼玉2")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(true))
				.andExpect(jsonPath("$.data.teacherUUID").value("7868c4a2e25c49338ff8105920f7065f"))
//				.andExpect(jsonPath("$.message").value("老师下面的学生不能有相同的名字"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	@Test
	public void deleteTeacherAndStudentByName() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String reuslt = mockMvc.perform(
				get("/teacher/delete").param("name", "张曼玉2")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(true))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}



	/**
	 * 同一个老师下有重名的学生
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersStudentNameSame() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"teacherUUID\":\"82d8b30087814825aa796f8fcdfd094c\",\"name\": \"张曼玉1\",\"sex\": \"女\",\"address\": \"中国杭州XXXX\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("10002"))
				.andExpect(jsonPath("$.message").value("老师下面的学生不能有相同的名字"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 正确添加1
	 *
	 * @throws Exception
	 */
	@Test
	public void addWell1() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"女\",\"address\": \"中国杭州XXXX\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(true))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 正确添加2
	 *
	 * @throws Exception
	 */
	@Test
	public void addWell2() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉2\",\"sex\": \"女\",\"address\": \"中国杭州XXXX\",\"studentList\": [{\"name\": \"张三1\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四1\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(true))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师名重复
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersNameSame() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"女\",\"address\": \"中国杭州XXXX\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("10001"))
				.andExpect(jsonPath("$.message").value("已经存在同名的老师"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师的名字为空
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersNameIsNull() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"\",\"sex\": \"男\",\"address\": \"中国杭州XXXX\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("老师名不能为空"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师的性别为空
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersSexIsNull() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"\",\"address\": \"中国杭州XXXX\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("老师性别不能为空"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师的地址为空
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersAddressIsNull() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"男\",\"address\": \"\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}, { \"name\": \"李四\", \"sex\": \"女\",\"address\": \"中国北京XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("老师住址不能为空"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师下的学生为空
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersStudentListIsNull() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"男\",\"address\": \"杭州\",\"studentList\": []}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("老师下面必须要有学生"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师的属性正确
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersAttributeIsOk() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"男\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 老师的性别是123
	 *
	 * @throws Exception
	 */
	@Test
	public void teachersSexIsError() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"123\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("老师性别填写错误"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 学生的性别是123
	 *
	 * @throws Exception
	 */
	@Test
	public void studentsSexIsError() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"123\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"123\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("学生性别填写错误"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 学生的年龄是9岁
	 *
	 * @throws Exception
	 */
	@Test
	public void studentsAgeIsNine() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"男\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"9\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("学生年龄不能小于10"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 学生的年龄是16岁
	 *
	 * @throws Exception
	 */
	@Test
	public void studentsAgeIs16() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"男\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"16\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.requestIsOk").value(false))
				.andExpect(jsonPath("$.code").value("VALID_ERROR_CODE"))
				.andExpect(jsonPath("$.message").value("学生年龄不能大于15"))
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 学生的年龄是10岁
	 *
	 * @throws Exception
	 */
	@Test
	public void studentsAgeIs10() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"男\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"10\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 学生的年龄是15岁
	 *
	 * @throws Exception
	 */
	@Test
	public void studentsAgeIs15() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"name\": \"张曼玉\",\"sex\": \"女\",\"address\": \"杭州\",\"studentList\": [{\"name\": \"张三\",\"sex\": \"男\",\"address\": \"中国上海XXX\",\"age\" : \"15\"}]}";
		String reuslt = mockMvc.perform(post("/teacher/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		System.out.println(reuslt);
	}

	/**
	 * 直接调用Service
	 *
	 * @throws Exception
	 */
	@Test
	public void testService() throws Exception {

		Date date = new Date();
		System.out.println(date.getTime());
		Result result = teacherService.queryTeacherAndStudentByName("张曼玉");
		assertTrue(result.isRequestIsOk());
//		assertFalse(result.isRequestIsOk());
		assertEquals(((TeacherStudentFrom)result.getData()).getName(),"张曼玉");
		System.out.println(JSONObject.toJSON(result));
	}
}
