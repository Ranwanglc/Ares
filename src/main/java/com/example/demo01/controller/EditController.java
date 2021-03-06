package com.example.demo01.controller;


import com.example.demo01.mapper.ThesisMapper;
import com.example.demo01.model.ThesisWithBLOBs;
import com.example.demo01.model.User;
import com.example.demo01.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class EditController {


    @Autowired(required=false)
    private EditService editService;



    @GetMapping("/edit")
    public String edit()
    {
        return "edit";
    }

    @PostMapping("/edit")
    public String doEdit(
            @RequestParam("tittle") String tittle,
            @RequestParam("paragraph") String paragraph,
            @RequestParam("reference") String reference,
            HttpServletRequest request,
            Model model
            ) {
        model.addAttribute("tittle",tittle);
        model.addAttribute("paragraph",paragraph);
        model.addAttribute("reference",reference);
        if(tittle==null||tittle=="")
        {
            model.addAttribute("error","题目不能为空");
            return "edit";
        }
        if(paragraph==null||paragraph=="")
        {
            model.addAttribute("error","段落不能为空");
            return "edit";
        }
        if(reference==null||reference=="")
        {
            model.addAttribute("error","参考文献不能为空");
            return "edit";
        }


        User user=(User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "edit";
        }

        ThesisWithBLOBs thesis1= new ThesisWithBLOBs();
        thesis1.setTittle(tittle);
        thesis1.setParagraph(paragraph);
        thesis1.setReference(reference);
        thesis1.setCreator(user.getId());
        thesis1.setGmtCreate(System.currentTimeMillis());
        thesis1.setGmtModified(thesis1.getGmtCreate());
        
        editService.insert(thesis1);

        return "redirect:/";
    }


    public void CreateLatex() throws IOException, InterruptedException {


            String [] cmd={"cmd.exe","/k","xelatex document.tex"};
            Process proc =Runtime.getRuntime().exec(cmd);
            new Thread(new SerializeTask(proc.getInputStream())).start();
            new Thread(new SerializeTask(proc.getErrorStream())).start();
            int exitVal = proc.waitFor();
            proc.getOutputStream().close();



            proc.destroy();

    }
    class SerializeTask implements Runnable{
        private InputStream in;

        public SerializeTask(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(br!=null)
                        br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
