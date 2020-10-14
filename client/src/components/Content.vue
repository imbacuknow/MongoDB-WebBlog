<template>
     <div id="Content" align="center">
          <br />
          <v-card
               class="justify-start ma-3 pb-2 rounded-lg amber lighten-4"
               width="800"
               height="auto"
               max-height="2000"
          >
               <v-col cols="12" sm="5" md="10">
                    <v-card-title>{{ set_content.title }}</v-card-title>
               </v-col>

               <v-col class="mt-n7" cols="12" sm="8" md="11">
                    <v-card-text align="left">{{
                         set_content.story
                    }}</v-card-text>
               </v-col>

               <v-col class="my-2" cols="12" sm="5" md="8">
                    <v-btn
                         class="ma-2"
                         @click="Confirm"
                         x-large
                         color="green darken-1"
                         dark
                    >
                         Confirm
                    </v-btn>
                    <v-btn
                         class="ma-2"
                         @click="Cancel"
                         x-large
                         color="deep-orange darken-3"
                         dark
                    >
                         Cancel
                    </v-btn>
               </v-col>
          </v-card>
     </div>
</template>

<script>
import api from "../api";
export default {
     name: "Content",
     props: ["content_id"],
     data: () => ({
          set_content: [],
     }), 
     mounted() {
          this.getContent();
     },
     methods: {
          getContent() {
               api.get("/webblog/post/content/" + this.content_id)
               .then((response) => {
                    this.set_content = response.data;
                    console.log(JSON.parse(JSON.stringify(response.data)));
               })
               .catch((e) => {
                    console.log("Error in getContent():" + e);
               });
          },
          Confirm() {
               api.put("/webblog/post/update/" + this.content_id)
               .then(() => {
                    this.$router.push("/Upcoming");
               })
               .catch((e) => {
                    console.log("error in Comfirm(): " + e);
               })
          },
          Cancel() {
               api.delete("/webblog/post/delete/" + this.set_content.id)
               .then(() => {
                    this.$router.push("/Upcoming");
               })
               .catch((e) => {
                    console.log("error in Cancel():" + e);
               });
           },
     }
}
</script>