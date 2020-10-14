<template>
     <div align="center">
          <v-row align="center" justify="center">
               <v-col cols="12" sm="8" md="6">
                    <v-alert type="success" dismissible v-model="alertSuccess"
                         >บันทึกข้อมูลสำเร็จ</v-alert
                    >
                    <v-alert type="error" dismissible v-model="alertFailed"
                         >{{ alertmsg }}!</v-alert
                    >
               </v-col>
          </v-row>
          <v-card
               class="justify-start ma-3 pb-2 rounded-lg red lighten-3"
               width="750"
               height="auto"
               max-height="2000"
          >
               <br />
               <v-col cols="12" sm="8" md="10">
                    <v-text-field
                         v-model="ins_title"
                         label="Title"
                         width="400"
                         solo
                    />
               </v-col>

               <!-- tag   -->
               <v-col cols="12" sm="8" md="10">
                    <v-combobox
                         v-model="model"
                         :filter="filter"
                         :hide-no-data="!search"
                         :items="items"
                         :search-input.sync="search"
                         hide-selected
                         label="Search for an option"
                         multiple
                         small-chips
                         solo
                    >
                         <template v-slot:no-data>
                              <v-list-item>
                                   <span class="subheading">Create</span>
                                   <v-chip
                                        :tagColor="`${
                                             colors[nonce - 1]
                                        } lighten-3`"
                                        label
                                        small
                                   >
                                        {{ search }}
                                   </v-chip>
                              </v-list-item>
                         </template>
                         <template
                              v-slot:selection="{
                                   attrs,
                                   item,
                                   parent,
                                   selected,
                              }"
                         >
                              <v-chip
                                   v-if="item === Object(item)"
                                   v-bind="attrs"
                                   :color="`${item.color} lighten-3`"
                                   :input-value="selected"
                                   label
                                   small
                              >
                                   <span class="pr-2">{{ item.text }}</span>
                                   <v-icon
                                        small
                                        @click="parent.selectItem(item)"
                                        >mdi-close</v-icon
                                   >
                              </v-chip>
                         </template>
                         <template v-slot:item="{ index, item }">
                              <v-text-field
                                   v-if="editing === item"
                                   v-model="editing.text"
                                   autofocus
                                   flat
                                   background-color="transparent"
                                   hide-details
                                   solo
                                   @keyup.enter="edit(index, item)"
                              ></v-text-field>
                              <v-chip
                                   v-else
                                   :color="`${item.color} lighten-3`"
                                   dark
                                   label
                                   small
                                   >{{ item.text }}</v-chip
                              >
                              <v-spacer></v-spacer>
                              <v-list-item-action @click.stop>
                                   <v-btn
                                        icon
                                        @click.stop.prevent="edit(index, item)"
                                   >
                                        <v-icon>{{
                                             editing !== item
                                                  ? "mdi-pencil"
                                                  : "mdi-check"
                                        }}</v-icon>
                                   </v-btn>
                              </v-list-item-action>
                         </template>
                    </v-combobox>
               </v-col>
               <!-- ^^^^ tag ^^^^ -->

               <v-col class="mt-n7" cols="12" sm="8" md="10">
                    <v-textarea
                         multi-line
                         v-model="ins_story"
                         label="Story Area"
                         counter
                         maxlength="5000"
                         full-width
                         single-line
                         background-color="#ffffff"
                         height="auto"
                    />
               </v-col>

               <v-col class="my-2" cols="12" sm="5" md="8">
                    <v-btn @click="checkPost" x-large color="secondary" dark
                         >Post</v-btn
                    >
               </v-col>
          </v-card>
     </div>
</template>

<script>
import api from "../api.js";
export default {
     data: () => ({
          // Post
          ins_title: undefined,
          ins_story: undefined,
          alertFailed: false,
          alertSuccess: false,
          alertmsg: undefined,
          user: {},
          ArrName: [],

          // Tag
          activator: null,
          attach: null,
          colors: ["green", "purple", "indigo", "cyan", "teal", "orange"],
          editing: null,
          index: -1,
          items: [],
          nonce: 1,
          menu: false,
          model: [],
          storageNewTag: [],
          x: 0,
          search: null,
          y: 0,
     }),
     // Tag
     watch: {
          model(val, prev) {
               if (val.length === prev.length) return;
               this.model = val.map((v) => {
                    if (typeof v === 'string') {
                         v = {
                              text: v,
                              color: this.random_color(this.colors),
                         };
                         this.items.push(v);
                         this.nonce++;
                    }
                    return v;
               });
               // console.log("ArrName:");
               // console.log(JSON.parse(JSON.stringify(this.ArrName)));
               // console.log("StorageNewTag: ");
               // console.log(JSON.parse(JSON.stringify(this.storageNewTag)));
          },
     },
     mounted() {
          this.getTag();
          this.user = JSON.parse(localStorage.getItem("user"));
     },
     methods: {
          // Tag
          random_color(colors) {
               return colors[Math.floor(Math.random() * colors.length)];
          },
          // Tag
          getTag() {
               api.get("/webblog/tag/showall")
                    .then((response) => {
                         this.items = response.data;
                         console.log("items");
                         console.log(JSON.parse(JSON.stringify(response.data)));
                    })
                    .catch((e) => {
                         console.log("Error in getTag():" + e);
                    });
          },
          // Tag
          edit(index, item) {
               if (!this.editing) {
                    this.editing = item;
                    this.index = index;
               } else {
                    this.editing = null;
                    this.index = -1;
               }
          },
          filter(item, queryText, itemText) {
               if (item.header) return false;
               const hasValue = (val) => (val != null ? val : "");
               const text = hasValue(itemText);
               const query = hasValue(queryText);
               return (
                    text
                         .toString()
                         .toLowerCase()
                         .indexOf(query.toString().toLowerCase()) > -1
               );
          },
          // Post
          checkPost() {
               if (!this.ins_title || !this.ins_story) {
                    this.clearAlert();
                    this.alertmsg = "กรุณากรอกข้อมูลให้ครบ";
                    this.alertFailed = true;
               } else {
                    this.pushTagArr();
               }
          },
          // Tag
          pushTagArr() {
               for (let i = 0; i < this.model.length; i++) {
                    if (!this.model[i].id) {
                         let v = {
                              text: this.model[i].text,
                              color: this.model[i].color
                         }
                         this.storageNewTag.push(v); // for add new tag
                         this.ArrName.push(this.model[i].text);
                    } else {
                         this.ArrName.push(this.model[i].text);
                    }
               }
               // console.log("ArrName:");
               // console.log(JSON.parse(JSON.stringify(this.ArrName)));
               // console.log("StorageNewTag: ");
               // console.log(JSON.parse(JSON.stringify(this.storageNewTag)));

               this.addTag();
          },
          // Tag
          addTag() {
               api.post("/webblog/tag/add", this.storageNewTag)
               .then(() => {
                    console.log("Added Tags");
               })
               .catch((e) => {
                    console.log("Error in addTag(): " + e);
               });
               this.postYourContent();
          },
          // Post
          postYourContent() {
               let databox = {
                    title: this.ins_title,
                    story: this.ins_story,
                    user_id: this.user.id,
                    tags: this.ArrName,
               };
               api.post("/webblog/post/create", databox)
               .then(() => {
                    this.clearAlert();
                    this.alertSuccess = true;

                    this.ins_title = null;
                    this.ins_story = null;
                    this.ArrName = null;
                    this.model = null;
                    this.storageNewTag = null;
                    // this.getTag();
                    window.location.href = "/Post";
               }).catch((e) => {
                    console.log("Error in postYourContent(): " + e);
               });
          },
          // Post
          clearAlert() {
               this.alertSuccess = false;
               this.alertFailed = false;
          },
     }
};
</script>