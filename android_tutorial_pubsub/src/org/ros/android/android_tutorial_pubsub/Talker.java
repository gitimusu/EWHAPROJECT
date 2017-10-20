package org.ros.android.android_tutorial_pubsub;

/**
 * Created by user on 2017-09-06.
 */


//package org.ros.android.android_tutorial_pubsub;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;

import org.ros.concurrent.CancellableLoop;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;

import javax.security.auth.SubjectDomainCombiner;

    public class Talker extends AbstractNodeMain {
        private String pub_pad_order_topic_name;
        private String sub_service_status_topic_name;
        private String pub_pad_status_topic_name;


        private int robot_num = 1;
        private int selected_item_num = -1;

        private boolean jump = false;

        private int[] item_num_chosen_by_pad = {-1, -1, -1};
        private int[] is_item_available = {1, 1, 1};
        private int[] robot_service_sequence = {0, 0, 0};

        public boolean[] button_pressed = {false, false, false};


        public Talker() {
            this.pub_pad_order_topic_name = "/tb3g/pad_order";
            this.sub_service_status_topic_name = "/tb3g/service_status";
            this.pub_pad_status_topic_name = "/tb3g/pad_status";
        }

        public GraphName getDefaultNodeName() {
            return GraphName.of("tb3g/pad");
        }

        public void onStart(ConnectedNode connectedNode) {
            final Publisher pub_pad_order = connectedNode.newPublisher(this.pub_pad_order_topic_name, "std_msgs/String");
            final Publisher pub_pad_status = connectedNode.newPublisher(this.pub_pad_status_topic_name, "std_msgs/String");

            final Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber(this.sub_service_status_topic_name, "std_msgs/String");

            subscriber.addMessageListener(new MessageListener<std_msgs.String>() {
                @Override
                public void onNewMessage(std_msgs.String strServiceStatus)
                {
                    String[] input_string_array = strServiceStatus.getData().split(",");

                    int[] input_numbers = new int[input_string_array.length];

                    for (int i = 0; i < input_string_array.length; i++)
                    {
                        input_numbers[i] = Integer.parseInt(input_string_array[i]);
                    }

                    item_num_chosen_by_pad[0] = input_numbers[0];
                    item_num_chosen_by_pad[1] = input_numbers[1];
                    item_num_chosen_by_pad[2] = input_numbers[2];
                    is_item_available[0] = input_numbers[3];
                    is_item_available[1] = input_numbers[4];
                    is_item_available[2] = input_numbers[5];
                    robot_service_sequence[0] = input_numbers[6];
                    robot_service_sequence[1] = input_numbers[7];
                    robot_service_sequence[2] = input_numbers[8];


                }
            });

            connectedNode.executeCancellableLoop(new CancellableLoop() {

                protected void setup() {
                }

                protected void loop() throws InterruptedException
                {
                    String str = " ";

                    Log.e("TAG", "0");

                    std_msgs.String strPadStatus = (std_msgs.String) pub_pad_status.newMessage();
                    std_msgs.String strPadOrder = (std_msgs.String) pub_pad_order.newMessage();

                    Log.e("TAG", "1");

                    Log.e("TAG", "2");

                    if (button_pressed[0] || button_pressed[1] || button_pressed[2])
                    {
                        Log.e("TAG", "3");

                        jump = false;

                        Log.e("TAG", "4");

                        if (button_pressed[0])
                        {
                            Log.e("TAG", "5");
                            selected_item_num = 0;
                            Log.e("TAG", "6");
                            str += "table1";

                            button_pressed[0] = false;
                        }
                        else if (button_pressed[1])
                        {
                            selected_item_num = 1;
                            str += "table2";

                            button_pressed[1] = false;
                        }
                        else if (button_pressed[2])
                        {
                            selected_item_num = 2;
                            str += "table3";

                            button_pressed[2] = false;
                        }
                        else
                        {
                            selected_item_num = -1;
                            str += "Sorry, selected table is now unavailable. Please choose another table.";
                        }


                        if (is_item_available[selected_item_num] != 1)
                        {
                            str += ", but chosen item is currently unavailable.";
                            jump = true;
                        }
                        else if (robot_service_sequence[robot_num] != 0)
                        {
                            str += ", but your TurtleBot is currently on servicing";
                            jump = true;
                        }
                        else if (item_num_chosen_by_pad[robot_num] != -1)
                        {
                            str += ", but your TurtleBot is currently on servicing";
                            jump = true;
                        }

                        strPadStatus.setData(str);
                        pub_pad_status.publish(strPadStatus);

                        if(!jump)
                        {
                            strPadOrder.setData(robot_num + "," + selected_item_num);
                            pub_pad_order.publish(strPadOrder);
                        }
                    }

                    Thread.sleep(1000L);
                }
            });
        }
    }


