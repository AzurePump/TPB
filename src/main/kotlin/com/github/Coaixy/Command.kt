package com.github.Coaixy

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.function.server

@CommandHeader("tpb", permissionDefault = PermissionDefault.TRUE)
object Command {
    @CommandBody(permissionDefault = PermissionDefault.FALSE, permission = "TPB.MAIN")
    val main = mainCommand {
        dynamic(optional = true) {
            execute<ProxyPlayer> { player, _, argument ->
                val list = argument.split(" ")
                if (list.size == 3 && player.hasPermission("TPB.MAIN")){
                    val x = list[0].toDouble()
                    val y = list[1].toDouble()
                    val z = list[2].toDouble()
//                    val cmd = "execute as "+player.name +" in 世界 run teleport $x $y $z"
                    var cmd = ""
                    when(player.world){
                        "world" ->{
                            cmd = "execute as "+player.name +" in minecraft:overworld run teleport $x $y $z"
                        }
                        "world_the_end" ->{
                            cmd = "execute as "+player.name +" in minecraft:the_end run teleport $x $y $z"
                        }
                        "world_nether" ->{
                            cmd = "execute as "+player.name +" in minecraft:the_nether run teleport $x $y $z"
                        }
                    }
//                    player.sendMessage(cmd)
//                    player.sendMessage(player.world)
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd)
                }else{
                    player.sendMessage("§4你没有使用此命令的权限或者参数错误")
                }
            }
        }
    }
}