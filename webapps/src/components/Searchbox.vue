<template>
  <div class="row">
    <div class="col-md-8 col-lg-8"></div>
    <div class="col-sm-12 col-md-4 col-lg-4">
      <input @keydown.enter="onKeydownEnter" class="form-control" placeholder="Job Name" type="text" v-model="jobName">
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Searchbox',
    data: function () {
      return {
        jobName: ''
      };
    },
    methods: {
      onKeydownEnter: function () {
        if (!this.jobName || this.jobName === '') {
          return;
        }

        this.$emit('onKeydownEnter', this.jobName);
      }
    }
  }
</script>
