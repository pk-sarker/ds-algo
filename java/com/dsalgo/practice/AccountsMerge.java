package com.dsalgo.practice;

import java.util.*;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements
 * are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the
 * same person if there is some common email to both accounts. Note that even if
 * two accounts have the same name, they may belong to different people as people could
 * have the same name. A person can have any number of accounts initially, but all of
 * their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first
 * element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
class AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parents = new HashMap<>();
        Map<String, String> accountName = new HashMap<>();
        Map<String, TreeSet<String>> union = new HashMap<>();

        // Considering emails as node
        // First for each email, make itself its parent
        for(List<String> account: accounts) {

            for(int i=1; i<account.size();i++) {
                parents.put(account.get(i), account.get(i));
                accountName.put(account.get(i), account.get(0));
            }
        }

        // For each account set a common parent for each email.
        for(List<String> account: accounts) {
            String parent = find(account.get(1), parents);
            for(int i=2; i<account.size();i++) {
                parents.put(find(account.get(i), parents), parent);
            }
        }
        // merge emails/nodes which has common root/parent
        for(List<String> account: accounts) {
            String parent = find(account.get(1), parents);
            if (!union.containsKey(parent)) {
                union.put(parent, new TreeSet<String>());
            }
            for(int i=1; i<account.size();i++) {
                union.get(parent).add(account.get(i));
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(String key: union.keySet()) {
            List<String> emails = new ArrayList<>(union.get(key));
            emails.add(0, accountName.get(key));
            result.add(emails);
        }
        return result;
    }

    public String find(String ac, Map<String, String> parents) {
        if (parents.get(ac) == ac) {
            return ac;
        } else {
            return find(parents.get(ac), parents);
        }
    }
    public static void main(String args[]) {
        AccountMerge obj = new AccountMerge();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john_newyork@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john00@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"Mary","mary@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnnybravo@mail.com"}));
        List<List<String>> res = obj.accountsMerge(accounts);
        System.out.println(res);
    }
}