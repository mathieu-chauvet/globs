package org.globsframework.network.impl;

import org.globsframework.metamodel.GlobType;
import org.globsframework.model.Glob;
import org.globsframework.model.GlobList;
import org.globsframework.network.NetworkService;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.nio.channels.*;
import java.util.Set;

public class DefaultNetworkService implements NetworkService {
  private ReceiverThread receiverThread;
  private String multicastAddr;
  private int port;
  private InetAddress multicastGroup;
  private MulticastSocket multicastSocket;
  private Selector selector;
//  private SerializationService serializationService;

  public DefaultNetworkService(String multicastAddr, int port) throws IOException {
    this.multicastAddr = multicastAddr;
    this.port = port;
    selector = Selector.open();
    receiverThread = new ReceiverThread(selector);
    ServerSocket accept = null;
    accept = new ServerSocket();
    accept(accept);
    int acceptPort = accept.getLocalPort();

    multicastSocket = new MulticastSocket(port);
    multicastGroup = InetAddress.getByName(multicastAddr);
    multicastSocket.joinGroup(multicastGroup);

    receiverThread.start();
  }

  public void publish(Glob glob, GlobList globList) {

  }

  public void registerListener(Client response, GlobType type) {
  }


  private static class ReceiverThread extends Thread {
    private final Selector selector;

    public ReceiverThread(Selector selector) {
      this.selector = selector;
    }

    public void run() {
      while (true) {
        try {
          selector.select();
          Set<SelectionKey> selectionKeySet = selector.selectedKeys();
          for (SelectionKey key : selectionKeySet) {
            OnReady o = (OnReady)key.attachment();
            o.run();
          }
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
    public void accept(ServerSocket socket) {
      ServerSocketChannel serverSocketChannel = socket.getChannel();
      try {
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptReady(this,  socket));
      }
      catch (ClosedChannelException e) {
        throw new RuntimeException(e);
      }
    }

    private static class AcceptReady implements OnReady {
      private DefaultNetworkService networkService;
      private ServerSocket socket;

      public AcceptReady(DefaultNetworkService networkService, ServerSocket socket) {
        this.networkService = networkService;
        this.socket = socket;
      }

      public void run() {
        try {
          Socket clientSocket = socket.accept();
          networkService.newClient(clientSocket);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
  }

  // ne doit etre appele que par le thread du selector
  private void newClient(final Socket socket) {
    try {
      SocketChannel socketChannel = socket.getChannel();
      SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
      selectionKey.attach(new InitialState(socket, selectionKey));

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  interface OnReady {
    void run();
  }

  private static class InitialState implements OnReady {
    private final Socket socket;
    private SelectionKey selectionKey;
    private InputStream stream;
    private DataInputStream dataInputStream;

    public InitialState(Socket socket, SelectionKey selectionKey) throws IOException {
      this.socket = socket;
      this.selectionKey = selectionKey;
      stream = socket.getInputStream();
      dataInputStream = new DataInputStream(stream);
    }

    public void run() {
      try {
        if (stream.available() > 4){
          int size = dataInputStream.readInt();
          
        }
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
