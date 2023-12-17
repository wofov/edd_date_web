package com.edd.date.controller.Admin;

import com.edd.date.configException.CustomException;
import com.edd.date.service.Admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags="보안인증")
@RestController
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @ApiOperation(value = "첫번째 인증", notes = "인증 받은 코드를 입력해 주세요.")
    @GetMapping("/admin/{code}")
    public ResponseEntity<String> authenticate(@ApiParam(value = "코드 값",required = true) @PathVariable(required = false) String code,
                                               @Value("${myapp.code}") String authenticate){
        return ResponseEntity.ok(adminService.codeAuthenticate(code,authenticate));
    }

    @ApiOperation(value = "JWT 생성")
    @GetMapping("/admin/token")
    public ResponseEntity<String> getJWTCreate() throws CustomException {
        return ResponseEntity.ok(adminService.JWT());
    }

    @ApiOperation(value = "JWT Decoder")
    @GetMapping("/admin/token-decoder")
    public ResponseEntity<String> getJWTDecoder(HttpServletRequest req) throws CustomException{
        return ResponseEntity.ok(adminService.JWTDecoder(req));
    }

}
