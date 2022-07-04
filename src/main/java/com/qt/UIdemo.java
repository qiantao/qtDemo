//import java.io.File;
//import java.io.FileInputStream;
//
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.ClientGlobal;
//import org.csource.fastdfs.ServerInfo;
//import org.csource.fastdfs.StorageClient;
//import org.csource.fastdfs.StorageServer;
//import org.csource.fastdfs.TrackerClient;
//import org.csource.fastdfs.TrackerServer;
//
///**
// * 测试上传
// * @author gary
// *
// */
//public class UIdemo {
//	//http://gary0416.iteye.com/blog/1148790
//    public static void main(String[] args) throws Exception{
//        String classPath = new File(UIdemo.class.getResource("/").getFile()).getCanonicalPath();
//        String configFilePath = classPath + File.separator + "fdfs_client.conf";
//        System.out.println("配置文件:" + configFilePath);
//
//        ClientGlobal.init(configFilePath);
//
//        TrackerClient trackerClient = new TrackerClient();
//        TrackerServer trackerServer = trackerClient.getConnection();
//
//        StorageServer storageServer = null;
//        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//
//        NameValuePair[] meta_list = new NameValuePair[3];
//        meta_list[0] = new NameValuePair("width", "120");
//        meta_list[1] = new NameValuePair("heigth", "120");
//        meta_list[2] = new NameValuePair("author", "gary");
//
////      byte[] file_buff = "F:\\pic.jpg".getBytes(ClientGlobal.g_charset);
//        File file = new File("F:\\pic.jpg");
//        FileInputStream fis = new FileInputStream(file);
//        byte[] file_buff = null;
//        if(fis != null){
//            int len = fis.available();
//            file_buff = new byte[len];
//            fis.read(file_buff);
//        }
//        System.out.println("file length: " + file_buff.length);
//
//        String group_name = null;
//        StorageServer[] storageServers = trackerClient.getStoreStorages(trackerServer, group_name);
//        if (storageServers == null) {
//            System.err.println("get store storage servers fail, error code: " + storageClient.getErrorCode());
//        }else{
//            System.err.println("store storage servers count: " + storageServers.length);
//            for (int k = 0; k < storageServers.length; k++){
//                System.err.println(k + 1 + ". " + storageServers[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + storageServers[k].getInetSocketAddress().getPort());
//            }
//            System.err.println("");
//        }
//
//        long startTime = System.currentTimeMillis();
//        String[] results = storageClient.upload_file(file_buff, "jpg", meta_list);
//        System.out.println("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
//
//        if (results == null){
//            System.err.println("upload file fail, error code: " + storageClient.getErrorCode());
//            return;
//        }
//
//        group_name = results[0];
//        String remote_filename = results[1];
//        System.err.println("group_name: " + group_name + ", remote_filename: " + remote_filename);
//        System.err.println(storageClient.get_file_info(group_name, remote_filename));
//
//        ServerInfo[] servers = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
//        if (servers == null){
//            System.err.println("get storage servers fail, error code: " + trackerClient.getErrorCode());
//        } else {
//            System.err.println("storage servers count: " + servers.length);
//            for (int k = 0; k < servers.length; k++){
//                System.err.println(k + 1 + ". " + servers[k].getIpAddr() + ":" + servers[k].getPort());
//            }
//            System.err.println("");
//
//        }
//    }
//
//        public void testUpload() throws Exception {
//            // 1、把FastDFS提供的jar包添加到工程中
//            // 2、初始化全局配置。加载一个配置文件。
//            ClientGlobal.init("D:\\workspaces-itcast\\JaveEE18\\taotao- fsm\\taotao-fsm-web\\src\\main\\resources\\properties\\client.conf");
//            // 3、创建一个TrackerClient对象。
//            TrackerClient trackerClient = new TrackerClient();
//            // 4、创建一个TrackerServer对象。
//            TrackerServer trackerServer = trackerClient.getConnection();
//            // 5、声明一个StorageServer对象，null。
//            StorageServer storageServer = null;
//            // 6、获得StorageClient对象。
//            StorageClient storageClient = new StorageClient(trackerServer,  storageServer);
//            // 7、直接调用StorageClient对象方法上传文件即可。
//            String[] strings = storageClient.upload_file("D:\\Documents\\Pictures\\images\\2f2eb938943d.jpg", "jpg", null);
////            storageClient.download_file(group_name, remote_filename, local_filename)
////            storageClient.delete_file(arg0, arg1);
//            for (String string : strings) {
//                System.out.println(string);
//            }
//    }
//}
