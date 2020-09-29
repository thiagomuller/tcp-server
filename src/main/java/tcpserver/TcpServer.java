package tcpserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TcpServer {
	public static void main(String[] args) {
		try {
			Selector selector = Selector.open();
			ServerSocketChannel serverSocket = ServerSocketChannel.open();
			InetSocketAddress serverAddr = new InetSocketAddress("localhost",
					5555);
			serverSocket.bind(serverAddr);
			serverSocket.configureBlocking(false);
			int ops = serverSocket.validOps();
			SelectionKey selectionKey = serverSocket.register(selector, ops);
			while (true) {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> keyIterator = keys.iterator();
				while(keyIterator.hasNext()) {
					SelectionKey key = keyIterator.next();
					if(key.isAcceptable()) {
						SocketChannel client = serverSocket.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
					}else if(key.isReadable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						client.read(buffer);
					}
					
				}
			}
		} catch (IOException io) {
			System.out.println("Couldn't create server socket or selector");
			io.printStackTrace();
		}
	}
}
