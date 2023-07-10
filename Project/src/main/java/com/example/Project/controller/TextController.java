package com.example.Project.controller;


import com.example.Project.model.Text;
import com.example.Project.service.TextServiceImpl;
import lombok.RequiredArgsConstructor;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
@RequestMapping("/texts")
public class TextController{

    String temp_unified_text="";

    String temp;

    int c;

    private final TextServiceImpl textService;


    @GetMapping("/add")
    public String Test(Model model){

        Text text = new Text();

        model.addAttribute("text", text);

        return "Unification";

    }

    @GetMapping("/db_save")
    public String Db_Write(Model model){



        return "HomePage";

    }


    @GetMapping("/homepage")
    public String HomePage(Model model){

        model.addAttribute("message","Siteye hoşgeldiniz");

        return "HomePage";

    }

    @GetMapping("/data")
    public String DataShow(Model model){

        model.addAttribute("texts", textService.getShowTexts());

        return "DataShow";
    }

    @PostMapping()
    public String SaveText(@ModelAttribute("text") Text text,Model model) {

        long start_time=0;
        long end_time=0;

        start_time= System.currentTimeMillis(); // Başlama zamanı

        ArrayList<String> arr_temporary=new ArrayList<String>();

        arr_temporary.add(text.getText1());
        arr_temporary.add(text.getText2());
        arr_temporary.add(text.getText3());
        arr_temporary.add(text.getText4());
        arr_temporary.add(text.getText5());
        arr_temporary.add(text.getText6());
        arr_temporary.add(text.getText7());
        arr_temporary.add(text.getText8());
        arr_temporary.add(text.getText9());
        arr_temporary.add(text.getText10());
        arr_temporary.add(text.getText11());
        arr_temporary.add(text.getText12());
        arr_temporary.add(text.getText13());
        arr_temporary.add(text.getText14());
        arr_temporary.add(text.getText15());

        int data_number=1;

        for(String str:arr_temporary ){

        if(str == null){

            break;

        }
        data_number +=1;

        }

        data_number -=1;

        ArrayList<String> arr_permanent=new ArrayList<String>();

        for(int i=0;i<data_number;i++){

          arr_permanent.add(arr_temporary.get(i));

        }

        String [] arr_comparison_str; //Kıyaslanacak metin

        ArrayList<String> arr_list_comparison_str=new ArrayList<String>();

        String [] arr_str;  //Diğer metinler

        String str; // Diğer metinlerin ilk elemanı


        int count=0;

        int count_temp=0;


        for(int i=0;i< arr_permanent.size();i++){ // Kıyaslanacak metin


            arr_comparison_str=arr_permanent.get(i).split(" "); // Kıyaslanacak metin bosluga göre parçalandı

            if(count==0){ // Benzerlik yoksa

                for (int n = 0; n < arr_comparison_str.length; n++) { //

                    arr_list_comparison_str.add(arr_comparison_str[n]); // Kıyaslanacak metin güncellendi

                }


            }


          for(int j=i+1;j< arr_permanent.size();j++){ // Diğer metinler

              temp=arr_permanent.get(j);

              arr_str=arr_permanent.get(j).split(" ");

              str=arr_str[0]; //  Diğer metinlerin ilk elemanı

              int array_count=1;

              for (int k = 0; k < arr_list_comparison_str.size(); k++) { // Kıyaslanacak metinin elemanları

                  if((str.contains(arr_list_comparison_str.get(k)))){ // diğer metinin ilk elemanı kıyaslanacak metinde var mı

                      count_temp +=1;

                      for (int p = 1; p < arr_str.length; p++) { // Diğer metinin ilk elemanı kıyaslanacak metinde varsa kalan elemanlarda var mı kontrolü

                          if(k+array_count<arr_list_comparison_str.size()){ // kıyaslanacak metinin boyutunun aşılıp aşılmadığını kontrol etme

                              if(arr_str[p].contains(arr_list_comparison_str.get(k+array_count))){ // var ise ondan sonra sıralı şekilde benzerlik olup olmama kontrolü


                                  array_count +=1;
                                  count_temp +=1; // Benzer kelime sayısı

                              }



                          }



                      }

                      if(count_temp == (arr_list_comparison_str.size())-k){ // Diğer metinin ilk elemanın kıyaslanacak metindeki sırasını kullanarak benzerlik kontrolü


                          count +=1; // Benzerlik var


                          String[] arr_result=temp.split(" ");

                          c=count_temp;

                          if(!(arr_list_comparison_str.get((arr_list_comparison_str.size()) - 1).contains(arr_result[c - 1]))){


                              arr_list_comparison_str.set((arr_list_comparison_str.size())-1, arr_result[c-1]);


                              ArrayList<String> arr_list_temp=new ArrayList<String>();
                              String [] arr_temp;

                              arr_temp=temp_unified_text.split(" "); // Kıyaslanacak metin bosluga göre parçalandı

                              for (int l = 0; l < arr_temp.length; l++) {

                                  arr_list_temp.add(arr_temp[l]); // Kıyaslanacak metin güncellendi

                              }
                              arr_list_temp.remove((arr_list_temp.size())-1);
                              arr_list_temp.add( arr_result[c-1]);

                              String temp_temp_unified_text="";

                              for (int p = 0; p < arr_list_temp.size(); p++) {



                                  temp_temp_unified_text += arr_list_temp.get(p)+" ";

                              }

                                        temp_unified_text=temp_temp_unified_text;


                          }


                          for (int n = count_temp; n < arr_result.length; n++) {


                              arr_list_comparison_str.add(arr_result[n]); // Kıyaslanacak metin güncellendi

                          }
                                if(count == 1){ // tek bir cümleyle benzerlik

                                    for (int p = 0; p < arr_list_comparison_str.size(); p++) {


                                        temp_unified_text += arr_list_comparison_str.get(p)+" ";

                                    }

                                }
                                else if(count>1){ // birden fazla cümleyle benzerlik


                                    for (int p = count_temp+k; p < arr_list_comparison_str.size(); p++) {


                                        temp_unified_text += arr_list_comparison_str.get(p)+" ";

                                    }

                                }



                      }



                  }



              }

              count_temp=0;






          }



        }

        if(count==0){ // Benzerlik bulunamadıysa

          temp_unified_text=temp; // En son girilen metin atandı

        }

        end_time= System.currentTimeMillis(); // Bitme zamanı

        double time = (double)(end_time-start_time)/1000; // Birleştirme için geçen süre


        text.setUnified_text(temp_unified_text);
        text.setTime(time);
        textService.SaveText(text);


        temp_unified_text="";

       return "Unification";


    }





}