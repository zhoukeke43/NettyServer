package chinobot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Hello world!
 *
 */
//https://www.cnblogs.com/lbhym/p/12753314.html
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println("客户端"+ch.remoteAddress()+"连接...");
                        ch.pipeline().addLast(new FirstServerHandler());
                    }

                });

        serverBootstrap.bind(3333);

    }
}
