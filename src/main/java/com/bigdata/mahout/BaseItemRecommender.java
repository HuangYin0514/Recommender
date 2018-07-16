package com.bigdata.mahout;

import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA by User1071324110@qq.com
 *
 * 与基于用户的技术不同的是，这种方法比较的是内容项与内容项之间的相似度。
 * Item-based 方法同样需要进行三个步骤获得推荐：
 * 1）得到内容项（Item）的历史评分数据；
 * 2）针对内容项进行内容项之间的相似度计算，找到目标内容项的“最近邻居”；
 * 3）产生推荐。这里内容项之间的相似度是通过比较两个内容项上的用户行为选择矢量得到的。
 * 第二代协同过滤算法
 * @author 10713
 * @date 2018/7/16 11:04
 */
public class BaseItemRecommender {

    public static void main(String[] args) throws Exception {
        //准备数据 这里是电影评分数据
        File file = new File("C:\\Users\\10713\\Desktop\\ratings.dat");
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        GroupLensDataModel dataModel = new GroupLensDataModel(file);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        PearsonCorrelationSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, similarity);
        //给用户ID等于5的用户推荐10个与2398相似的商品
        List<RecommendedItem> recommendedItems = recommender.recommendedBecause(47, 1240, 10);
        //打印推荐的结果
        for (RecommendedItem recommendedItem : recommendedItems) {
            System.out.println(recommendedItem);
        }
    }
}
