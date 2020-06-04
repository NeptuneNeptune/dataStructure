package data.structure.sample.traverse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * 遍历文件夹内容
 * 目前提供三种遍历方式：参考{@link TraverseStrategy}<br/>
 * 递归遍历，深度优先遍历，广度优先遍历
 *
 * @author Neptune
 * @date 2020/6/3 15:50
 */
public class TraverseDir {
    /**
     * 文件夹信息
     */
    private DirInfo dirInfo;

    /**
     * 获取解析后的文件夹信息
     *
     * @return DirInfo 文件夹信息
     */
    public DirInfo getDirInfo() {
        return dirInfo;
    }

    /**
     * 创建一个文件夹遍历
     *
     * @param root     被遍历的文件夹
     * @param strategy 遍历策略 0->递归遍历  1->深度优先遍历  2->广度优先遍历
     */
    public TraverseDir(String root, TraverseStrategy strategy) throws IOException {
        // 合法性判断
        File rootFile = new File(root);
        if (!rootFile.exists()) {
            throw new FileNotFoundException("该目录或文件不存在");
        }
        // 获取绝对路径
        root = rootFile.getCanonicalPath();
        switch (strategy.getValue()) {
            // 递归遍历
            case 0:
                this.dirInfo = traverseRecursively(new DirInfo(null, root, 0));
                break;
            // 深度优先遍历
            case 1:
                this.dirInfo = depthFirstTraversal(new DirInfo(null, root, 0));
                break;
            // 广度优先遍历
            case 2:
                this.dirInfo = breadthFirstTraversal(new DirInfo(null, root, 0));
                break;
            default:
                throw new IllegalArgumentException("目前未提供这种遍历策略");
        }

    }

    /**
     * 递归遍历
     *
     * @param result 初始化之后的初始文件夹信息
     * @return DirInfo 文件夹信息
     */
    private DirInfo traverseRecursively(DirInfo result) throws IOException {
        File tempFile = new File(result.getName());
        if (tempFile.isFile()) {
            // 如果是文件，设置参数
            result.setChildren(null);
            result.setSize(tempFile.length());
            result.setType(0);
        } else {
            // 如果是文件夹，设置参数后，继续递归子文件，直到完毕
            result.setChildren(new ArrayList<>());
            result.setSize(tempFile.length());
            result.setType(1);

            for (File file : Objects.requireNonNull(tempFile.listFiles())) {
                File child = new File(result.getName(), file.getName());
                DirInfo tempChild = new DirInfo(result, child.getCanonicalPath(), result.getDepth() + 1);
                result.getChildren().add(tempChild);

                traverseRecursively(tempChild);
            }
        }
        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param result 初始化之后的初始文件夹信息
     * @return DirInfo 文件夹信息
     */
    private DirInfo depthFirstTraversal(DirInfo result) throws IOException {
        // 用于存放未处理的文件夹或文件
        Stack<DirInfo> stack = new Stack<>();
        // 初始化栈
        stack.push(result);
        // 进行遍历
        while (!stack.isEmpty()) {
            DirInfo temp = stack.pop();
            File tempFile = new File(temp.getName());
            if (tempFile.isFile()) {
                // 如果是文件
                temp.setChildren(null);
                temp.setType(0);
                temp.setSize(tempFile.length());
            } else {
                // 如果是文件夹
                // 设置父文件夹信息
                temp.setChildren(new ArrayList<>());
                temp.setType(1);
                temp.setSize(tempFile.length());
                // 将子文件夹或子文件压栈
                for (File file : Objects.requireNonNull(tempFile.listFiles())) {
                    DirInfo child = new DirInfo(
                            temp,
                            new File(temp.getName(), file.getName()).getCanonicalPath(),
                            temp.getDepth() + 1);
                    temp.getChildren().add(child);
                    stack.push(child);
                }
            }
        }

        return result;
    }

    /**
     * 广度优先遍历
     *
     * @param result 初始化之后的初始文件夹信息
     * @return DirInfo 文件夹信息
     */
    private DirInfo breadthFirstTraversal(DirInfo result) throws IOException {
        // 用于存放未处理的文件夹或文件
        Queue<DirInfo> queue = new ArrayDeque<>();
        // 初始化队列
        queue.add(result);
        // 进行遍历
        while (!queue.isEmpty()) {
            DirInfo temp = queue.poll();
            File tempFile = new File(temp.getName());
            if (tempFile.isFile()) {
                // 如果是文件
                temp.setChildren(null);
                temp.setType(0);
                temp.setSize(tempFile.length());
            } else {
                // 如果是文件夹
                // 设置父文件夹信息
                temp.setChildren(new ArrayList<>());
                temp.setType(1);
                temp.setSize(tempFile.length());
                // 将子文件夹或子文件入队
                for (File file : Objects.requireNonNull(tempFile.listFiles())) {
                    DirInfo child = new DirInfo(
                            temp,
                            new File(temp.getName(), file.getName()).getCanonicalPath(),
                            temp.getDepth() + 1);
                    temp.getChildren().add(child);
                    queue.add(child);
                }
            }
        }

        return result;
    }

    /**
     * 文件夹信息类
     */
    private static class DirInfo {
        /**
         * 父文件夹信息
         */
        private DirInfo parent;
        /**
         * 自身路径
         */
        private String name;
        /**
         * 类型
         * <p>
         * 0->文件  1->文件夹
         */
        private int type;
        /**
         * 子文件或子文件夹信息
         */
        private List<DirInfo> children;
        /**
         * 大小信息，单位字节
         */
        private long size;
        /**
         * 遍历深度，root文件夹自身为0
         */
        private int depth;

        /**
         * 创建一个文件夹信息
         *
         * @param parent 父目录，根目录地父目录信息为null
         * @param name   自身的路径
         * @param depth  遍历深度，初始为0
         */
        public DirInfo(DirInfo parent, String name, int depth) {
            this.parent = parent;
            this.name = name;
            this.depth = depth;
        }

        public DirInfo getParent() {
            return parent;
        }

        public void setParent(DirInfo parent) {
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<DirInfo> getChildren() {
            return children;
        }

        public void setChildren(List<DirInfo> children) {
            this.children = children;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }

    /**
     * 遍历策略
     */
    public enum TraverseStrategy{
        /**
         * 递归策略
         */
        RECURSIVE(0),
        /**
         * 深度优先策略
         */
        DEPTH_FIRST(1),
        /**
         * 广度优先策略
         */
        BREADTH_FIRST(2);

        /**
         * 策略代码
         */
        private int value;

        TraverseStrategy(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}


class Test {
    // 数据结构演示案例，文件夹遍历
    public static void main(String[] args) throws IOException {
        TraverseDir traverseDir0 = new TraverseDir(
                "./out/production/data_structure",
                TraverseDir.TraverseStrategy.RECURSIVE);
        TraverseDir traverseDir1 = new TraverseDir(
                "./out/production/data_structure",
                TraverseDir.TraverseStrategy.DEPTH_FIRST);
        TraverseDir traverseDir2 = new TraverseDir(
                "./out/production/data_structure",
                TraverseDir.TraverseStrategy.BREADTH_FIRST);

        System.out.println(traverseDir0.getDirInfo());
        System.out.println(traverseDir1.getDirInfo());
        System.out.println(traverseDir2.getDirInfo());
    }
}