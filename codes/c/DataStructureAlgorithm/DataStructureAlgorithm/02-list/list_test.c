//
//  list_test.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "list_test.h"
#include "List.h"

void print_list(char **array) {
    int i;
    for( i = 0; array[i]; i++)
        printf("%s", array[i]);
    printf("\n");
}

void list_test()
{
    List list1, list2, list3, list4;
    char **str1 = (char **)malloc(100 * sizeof(char *));

    list1 = list_init();
    list1 = list_push(list1, "Dang ");
    list1 = list_push(list1, "Hoang ");
    list1 = list_push(list1, "Hai ");
    printf("List 1: ");
    str1 = (char **)list_toArray(list1);
    print_list(str1);

    list2 = list_init();
    list2 = list_list(list2, "Mentor ", "Graphics ", "Siemens", NULL);
    printf("List 2: ");
    print_list((char **)list_toArray(list2));

    list3 = list_append(list1, list2);
    printf("Test append list2 into list1: ");
    print_list((char **)list_toArray(list3));
    
    list4 = list_copy(list3);
    printf("List 4: ");
    print_list((char **)list_toArray(list4));
    
    List pop_ele = list_pop(&list4);
    printf("pop_ele = %s\n", pop_ele->val);
    printf("List 4 pop : ");
    print_list((char **)list_toArray(list4));
}
