package com.threek.whatsapp.util;

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.Conversation;
import com.threek.whatsapp.model.FriendRequest;
import com.threek.whatsapp.model.Group;
import com.threek.whatsapp.model.Message;
import com.threek.whatsapp.model.Notification;
import com.threek.whatsapp.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    // Current user
    public static User getCurrentUser() {
        return new User("u0", "Alex Thompson", "alexthompson",
                "alex.thompson@email.com", R.drawable.ic_profile,
                true, "Active now", "March 2025", 0);
    }

    // Users
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("u1", "Emily Davis", "emilydavis",
                "emily.davis@company.com", R.drawable.ic_profile,
                true, "Active now", "March 2025", 12));
        users.add(new User("u2", "John Smith", "johnsmith",
                "john.smith@email.com", R.drawable.ic_profile,
                false, "2:30 PM", "January 2025", 8));
        users.add(new User("u3", "Sarah Johnson", "sarahjohnson",
                "sarah.johnson@email.com", R.drawable.ic_profile,
                false, "1:15 PM", "February 2025", 5));
        users.add(new User("u4", "Alex Chen", "alexchen",
                "alex.chen@email.com", R.drawable.ic_profile,
                true, "Active now", "June 2024", 15));
        users.add(new User("u5", "Lisa Wang", "lisawang",
                "lisa.wang@email.com", R.drawable.ic_profile,
                false, "Yesterday", "April 2025", 3));
        users.add(new User("u6", "Mike Rodriguez", "mikerod",
                "mike.rod@email.com", R.drawable.ic_profile,
                true, "Active now", "May 2024", 10));
        return users;
    }

    // Conversations
    public static List<Conversation> getConversations() {
        List<User> users = getUsers();
        List<Conversation> conversations = new ArrayList<>();

        conversations.add(new Conversation("c1", users.get(0),
                "Can we schedule a meeting for tomorro…", "3:45 PM", 0, false, null));
        conversations.add(new Conversation("c2", users.get(1),
                "Hey, how are you doing today?", "2:30 PM", 2, false, null));
        conversations.add(new Conversation("c3", users.get(2),
                "Thanks for the update! \uD83D\uDC4D", "1:15 PM", 0, false, null));

        Conversation groupConv = new Conversation("c4", null,
                "Mike: Let's review the campaign…", "12:45 PM", 5, true, "Marketing Team");
        conversations.add(groupConv);

        conversations.add(new Conversation("c5", users.get(3),
                "\uD83D\uDCCE Document_final.pdf", "11:30 AM", 0, false, null));
        conversations.add(new Conversation("c6", users.get(4),
                "Perfect! See you then.", "Yesterday", 0, false, null));

        return conversations;
    }

    // Messages for a conversation
    public static List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();

        Message m1 = new Message("m1", "u1", "Hey! How are you doing today?",
                "2:30 PM", Message.Type.TEXT, Message.Status.READ);
        messages.add(m1);

        Message m2 = new Message("m2", "u0",
                "I'm doing great, thanks! Just working on some projects.",
                "2:32 PM", Message.Type.TEXT, Message.Status.READ);
        messages.add(m2);

        Message m3 = new Message("m3", "u1",
                "That sounds interesting! What kind of projects are you working on?",
                "2:33 PM", Message.Type.TEXT, Message.Status.READ);
        messages.add(m3);

        Message m4 = new Message("m4", "u0",
                "Mostly web development stuff. Working on a new chat application actually!",
                "2:35 PM", Message.Type.TEXT, Message.Status.READ);
        messages.add(m4);

        Message m5 = new Message("m5", "u1",
                "Can we schedule a meeting to discuss this further?",
                "3:45 PM", Message.Type.TEXT, Message.Status.DELIVERED);
        messages.add(m5);

        Message m6 = new Message("m6", "u1",
                "Here's the document we discussed",
                "3:47 PM", Message.Type.FILE, Message.Status.DELIVERED);
        m6.setFileName("project-proposal.pdf");
        m6.setFileSize("245 KB");
        messages.add(m6);

        return messages;
    }

    // Group messages
    public static List<Message> getGroupMessages() {
        List<Message> messages = new ArrayList<>();

        Message m1 = new Message("gm1", "u2",
                "Hey everyone! Let's discuss the new feature requirements for the upcoming sprint.",
                "2:38 PM", Message.Type.TEXT, Message.Status.READ);
        messages.add(m1);

        Message m2 = new Message("gm2", "u1",
                "Great idea! I've prepared some mockups we can review. Should I share them here?",
                "2:40 PM", Message.Type.TEXT, Message.Status.READ);
        m2.setEdited(true);
        messages.add(m2);

        Message m3 = new Message("gm3", "u6",
                "Here are the detailed requirements we discussed last week.",
                "2:42 PM", Message.Type.FILE, Message.Status.DELIVERED);
        m3.setFileName("requirements.pdf");
        m3.setFileSize("2.3 MB");
        messages.add(m3);

        return messages;
    }

    // Notifications
    public static List<Notification> getNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("n1",
                "Emily Davis sent you a message",
                "Can we schedule a meeting for tomorrow?",
                "5 min ago", Notification.Type.MESSAGE, false));
        notifications.add(new Notification("n2",
                "John Smith sent you a message",
                "Hey, how are you doing today?",
                "12 min ago", Notification.Type.MESSAGE, false));
        notifications.add(new Notification("n3",
                "You were added to Marketing Team",
                "Mike added you to the group",
                "1 hr ago", Notification.Type.GROUP_INVITE, false));
        notifications.add(new Notification("n4",
                "Sarah Johnson sent you a message",
                "Thanks for the update! \uD83D\uDC4D",
                "2 hrs ago", Notification.Type.MESSAGE, true));
        notifications.add(new Notification("n5",
                "Alex Chen sent you a friend request",
                "Accept or decline the request",
                "3 hrs ago", Notification.Type.FRIEND_REQUEST, true));
        notifications.add(new Notification("n6",
                "System notification",
                "Your account settings have been updated",
                "1 day ago", Notification.Type.SYSTEM, true));
        return notifications;
    }

    // Friend Requests (Pending)
    public static List<FriendRequest> getPendingRequests() {
        List<FriendRequest> requests = new ArrayList<>();
        requests.add(new FriendRequest("fr1",
                new User("u10", "Michael Chen", "michaelchen", "", R.drawable.ic_profile, false, "", "", 5),
                5, true));
        requests.add(new FriendRequest("fr2",
                new User("u11", "Lisa Rodriguez", "lisarodriguez", "", R.drawable.ic_profile, false, "", "", 2),
                2, true));
        requests.add(new FriendRequest("fr3",
                new User("u12", "David Park", "davidpark", "", R.drawable.ic_profile, false, "", "", 8),
                8, true));
        requests.add(new FriendRequest("fr4",
                new User("u13", "Amanda Taylor", "amandataylor", "", R.drawable.ic_profile, false, "", "", 1),
                1, true));
        return requests;
    }

    // Suggested Friends
    public static List<FriendRequest> getSuggestedFriends() {
        List<FriendRequest> suggestions = new ArrayList<>();
        FriendRequest s1 = new FriendRequest("sf1",
                new User("u20", "Robert Kim", "robertkim", "", R.drawable.ic_profile, false, "", "", 6),
                6, false);
        s1.setInfo("Works at TechCorp");
        suggestions.add(s1);

        FriendRequest s2 = new FriendRequest("sf2",
                new User("u21", "Jennifer Wilson", "jenniferwilson", "", R.drawable.ic_profile, false, "", "", 4),
                4, false);
        s2.setInfo("Marketing Manager");
        suggestions.add(s2);

        FriendRequest s3 = new FriendRequest("sf3",
                new User("u22", "Alex Thompson", "alexthompson2", "", R.drawable.ic_profile, false, "", "", 3),
                3, false);
        s3.setInfo("Software Engineer");
        suggestions.add(s3);

        FriendRequest s4 = new FriendRequest("sf4",
                new User("u23", "Maria Garcia", "mariagarcia", "", R.drawable.ic_profile, false, "", "", 7),
                7, false);
        s4.setInfo("Product Designer");
        suggestions.add(s4);

        return suggestions;
    }

    // Search users
    public static List<User> getSearchUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("su1", "Jessica Brown", "jessicabrown",
                "jessica.brown@email.com", R.drawable.ic_profile, false, "", "", 0));
        users.add(new User("su2", "Ryan Miller", "ryanmiller",
                "ryan.miller@company.com", R.drawable.ic_profile, false, "", "", 0));
        users.add(new User("su3", "Samantha Lee", "samanthalee",
                "samantha.lee@startup.io", R.drawable.ic_profile, false, "", "", 0));
        users.add(new User("su4", "Kevin Wong", "kevinwong",
                "kevin.wong@techcorp.com", R.drawable.ic_profile, false, "", "", 0));
        users.add(new User("su5", "Nina Patel", "ninapatel",
                "nina.patel@design.studio", R.drawable.ic_profile, false, "", "", 0));
        return users;
    }

    // Blocked users
    public static List<User> getBlockedUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("bu1", "Mark Wilson", "markwilson",
                "", R.drawable.ic_profile, false, "", "", 0));
        users.add(new User("bu2", "David Brown", "davidbrown",
                "", R.drawable.ic_profile, false, "", "", 0));
        users.add(new User("bu3", "Jennifer Lee", "jenniferlee",
                "", R.drawable.ic_profile, false, "", "", 0));
        return users;
    }

    // Group info
    public static Group getGroupInfo() {
        List<User> members = new ArrayList<>();
        members.add(new User("u4", "Alex Chen", "alexchen", "", R.drawable.ic_profile, true, "Active now", "", 0));
        members.add(new User("u3", "Sarah Johnson", "sarahjohnson", "", R.drawable.ic_profile, false, "", "", 0));
        members.add(new User("u6", "Mike Rodriguez", "mikerod", "", R.drawable.ic_profile, true, "Active now", "", 0));
        members.add(new User("u30", "Emma Wilson", "emmawilson", "", R.drawable.ic_profile, false, "", "", 0));

        return new Group("g1", "Project Team", R.drawable.ic_contacts,
                "Oct 1, 2025", members,
                "Sarah Admin: Welcome everyone! Please check the project guidelines...",
                true);
    }
}
