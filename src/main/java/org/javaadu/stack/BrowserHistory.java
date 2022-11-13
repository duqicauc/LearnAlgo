package org.javaadu.stack;

import java.util.Stack;

public class BrowserHistory {

    /**
     * 后退栈
     */
    private Stack<String> backStack = new Stack<>();

    /**
     * 前进栈
     */
    private Stack<String> forwardStack = new Stack<>();

    public BrowserHistory(String homepage) {
        backStack.push(homepage);
    }

    public void visit(String url) {
        backStack.push(url);
        forwardStack.clear();
    }

    public String back(int steps) {
        for (int i = 0; i < steps && backStack.size() > 1; i++) {
            String urlI = backStack.pop();
            forwardStack.push(urlI);
        }
        return backStack.peek();
    }

    public String forward(int steps) {
        for (int i = 0; i < steps && !forwardStack.isEmpty(); i++) {
            String urlI = forwardStack.pop();
            backStack.push(urlI);
        }
        return backStack.peek();
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("google.com");
        // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("facebook.com");
        // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.visit("youtube.com");

        // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        System.out.println(browserHistory.back(1));
        // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        System.out.println(browserHistory.back(1));
        // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        System.out.println(browserHistory.forward(1));
        // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        browserHistory.visit("linkedin.com");
        // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        System.out.println(browserHistory.forward(2));
        // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        System.out.println(browserHistory.back(2));
        // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"
        System.out.println(browserHistory.back(7));
    }
}
