package com.sktelecom.t1.Applications;

import com.sktelecom.t1.Daos.PlayersMapper;
import com.sktelecom.t1.Models.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/Player")
public class PlayerController {

    @Autowired
    private PlayersMapper playersMapper;

    private static String picAdd;

    @RequestMapping("/addPic")
    public ResponseEntity<String> addPic(@RequestParam MultipartFile pic) throws IOException {
        if(!pic.isEmpty()){
            System.out.println(picAdd);
            String oldFileName = pic.getOriginalFilename();
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload/pic";
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File file = new File(path,newFileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();//建立多级文件夹
            }
            pic.transferTo(file);
            picAdd = path+"/"+newFileName;
            return new ResponseEntity<String>(picAdd, HttpStatus.ACCEPTED);
        }
        picAdd = "";
        return new ResponseEntity<String>("",HttpStatus.ACCEPTED);
    }
}
