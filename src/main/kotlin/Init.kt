package org.gptbot

import org.gptbot.ChatBot.BotBasic.path_preset
import org.json.JSONObject
import java.io.File

fun InitChatCfg(id : Long, private : Boolean =  true){
    //预设文件夹
    if(!File(path_preset,"group").exists())File(path_preset,"group").mkdir()
    var path = path_preset

    val json = JSONObject("""
            {
                "type":"default",
            }
            """.trimIndent())
    path = if(private) {
        "$path/$id"
//        json.put("private",false)
    }else{
        "$path/group/$id"
//        json.put("private",false)
    }
    //开启对话模式，记住聊天内容
    json.put("chat",true)
    //预设文件夹
    if(!File(path).exists())!File(path).mkdir()

    val file_Set = File(path,"set.json")
//    json.put("path",path)
    if(!file_Set.exists()){
        file_Set.writeText(json.toString())
    }
}

fun First_init(){
    val Folder_Chatbot = File("./config/Chatbot")
    if(!Folder_Chatbot.exists()) Folder_Chatbot.mkdir()

    val Folder_preset= File(Folder_Chatbot,"preset")
    if(!Folder_preset.exists()) Folder_preset.mkdir()

    val File_apikey = File(Folder_Chatbot,"key.txt")
    if (!File_apikey.exists()) File_apikey.writeText("Your apiKey")

    val File_master = File(Folder_Chatbot,"master.txt")
    if (!File_master.exists()) File_master.writeText("1223102221")

    val File_parameters = File(Folder_Chatbot,"parameters.json")
    if (!File_parameters.exists()) File_parameters.writeText("""
        {
            "model": "gpt-3.5-turbo",
            "temperature": 0.8,
            "frequency_penalty": 0,
            "presence_penalty": 0,
            "max_tokens": 2048,
            "Reverse-proxy":"api.openai-proxy.com"
        }
    """.trimIndent())

    File("./config/Chatbot/preset/格式").writeText("""
        [
            {
                "role": "system",
                "content":"一些提示"
            },
            {
                "role": "user",
                "content":"一些内容"
            },
            {
                "role": "assistant",
                "content":"希望他的回复"
            }
        ]
        #可以自己添加其他内容，role里的内容user和assistant交替
        """.trimIndent())

    File("./config/Chatbot/help.txt").writeText("""
        【】内的为触发关键字
        #key
        【/key~（你的openai的apikey）】
        
        #聊天相关
        【chat设置】设置chat参数，有提示，chat改false则为qa模式不记录对话内容
        【@机器（你的聊天内容）】开启单独对话（在群里@，但是对话内容是独立的）
        【~】开头，开启群聊（记录群聊中所以带内容）
        【结束对话】结束当前对话，群里发送结束群聊对话
        
        #预设相关
        【预设xx】进行预设，私聊修改@的预设，群聊修改群预设
        【添加预设】可以添加，
        【删除预设】会显示全部预设提供删除帮助
        【全部预设】查看全部预设
        """.trimIndent())
}