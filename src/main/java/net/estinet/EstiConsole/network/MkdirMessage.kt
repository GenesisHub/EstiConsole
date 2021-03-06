package net.estinet.EstiConsole.network

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import net.estinet.EstiConsole.EstiConsole
import java.io.File

class MkdirMessage : Message{
    override val name: String = "mkdir"
    override fun run(args: List<String>, session: SocketIOClient, ack: AckRequest) {
        try {
            if (File(args[0]).exists()) {
                if (EstiConsole.debug) {
                    EstiConsole.println("[Debug] mkdir request: Already exists. " + args[0])
                }
                ack.sendAckData("ecerror", "203")
            } else {
                if (EstiConsole.debug) {
                    EstiConsole.println("[Debug] mkdir request: Created " + args[0])
                }
                File(args[0]).mkdir()
                ack.sendAckData("good")
            }
        }
        catch(e: Throwable){
            e.printStackTrace()
            ack.sendAckData("ecerror", "901")
        }
    }
}