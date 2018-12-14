<template>
  <div class="row">
    <div class="col-sm-12 col-md-12 col-lg-12">
      <h1>Steps</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead>
          <tr>
            <th>Step Execution ID</th>
            <th>Step Name</th>
            <th>Start</th>
            <th>End</th>
            <th>Status</th>
            <th>Exit Code</th>
          </tr>
          </thead>
          <tbody>
          <tr @click="openModal(step)" v-for="step in stepList">
            <td>{{ step.stepExecutionId }}</td>
            <td>{{ step.stepName }}</td>
            <td>{{ step.startTime }}</td>
            <td>{{ step.endTime }}</td>
            <td>{{ step.status }}</td>
            <td>{{ step.exitCode }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="modal fade" ref="stepModal">
        <div @click="closeModal" class="modal-backdrop fade" ref="stepModalBackdrop" style="height: 100%;"></div>
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button @click="closeModal" aria-label="Close" class="close" data-dismiss="modal" type="button"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">{{ step.stepName }}</h4>
            </div>
            <div class="modal-body">
              <div class="table-responsive">
                <table class="table table-striped table-hover">
                  <tbody>
                  <tr>
                    <th>Step Execution ID</th>
                    <td>{{ step.stepExecutionId }}</td>
                  </tr>
                  <tr>
                    <th>Step Name</th>
                    <td>{{ step.stepName }}</td>
                  </tr>
                  <tr>
                    <th>Start</th>
                    <td>{{ step.startTime }}</td>
                  </tr>
                  <tr>
                    <th>End</th>
                    <td>{{ step.endTime }}</td>
                  </tr>
                  <tr>
                    <th>Status</th>
                    <td>{{ step.status }}</td>
                  </tr>
                  <tr>
                    <th>Commit(Rollback)</th>
                    <td>{{ step.commitCount }}({{ step.rollbackCount }})</td>
                  </tr>
                  <tr>
                    <th>Read(Skip)</th>
                    <td>{{ step.readCount }}({{ step.readSkipCount }})</td>
                  </tr>
                  <tr>
                    <th>ProcessSkip</th>
                    <td>{{ step.processSkipCount }}</td>
                  </tr>
                  <tr>
                    <th>Write(Skip)</th>
                    <td>{{ step.writeCount }}({{ step.writeSkipCount }})</td>
                  </tr>
                  <tr>
                    <th>Exit Code</th>
                    <td>{{ step.exitCode }}</td>
                  </tr>
                  <tr>
                    <th>Exit Message</th>
                    <td>{{ step.exitMessage }}</td>
                  </tr>
                  <tr>
                    <th>Last Updated</th>
                    <td>{{ step.lastUpdated }}</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="modal-footer">
              <button @click="closeModal" class="btn btn-default" data-dismiss="modal" type="button">Close</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'StepTable',
    props: ['jobExecutionId'],
    data: function () {
      return {
        stepList: [],
        step: {}
      }
    },
    computed: {
      pageList: function () {
        let lastIndex = this.page.total / this.page.size;

        let startIndex = this.page.index - 2 > 0 ? this.page.index - 2 : 1;
        let endIndex = this.page.index + 2 < lastIndex ? this.page.index + 2 : lastIndex;

        let pageList = [];
        for (let index = startIndex; index <= endIndex; index++) {
          pageList.push(index);
        }

        return pageList;
      }
    },
    watch: {
      jobExecutionId: function () {
        this.search(1);
      }
    },
    methods: {
      isCurrentPage: function (index) {
        return index === this.page.index;
      },
      search: function (pageIndex) {
        this.stepList = [];

        if (!this.jobExecutionId || this.jobExecutionId <= 0) {
          return;
        }

        axios.get('/spring/batch/api/stepExecutions/' + this.jobExecutionId, {
          headers: {Pragma: 'no-cache'},
        }).then((response) => {
          this.stepList = response.data;
        }).catch((error) => {
          console.dir(error);
        });
      },
      openModal: function (step) {
        this.step = step;

        const $stepModal = this.$refs.stepModal;
        const $stepModalBackdrop = this.$refs.stepModalBackdrop;
        $stepModal.style.display = "block";
        setTimeout(function () {
          $stepModal.classList.add('in');
          $stepModalBackdrop.classList.add('in');
        }, 500);
      },
      closeModal: function () {
        const $stepModal = this.$refs.stepModal;
        const $stepModalBackdrop = this.$refs.stepModalBackdrop;
        $stepModal.classList.remove('in');
        $stepModalBackdrop.classList.remove('in');
        setTimeout(function () {
          $stepModal.style.display = "none";
        }, 100);
      }
    }
  }
</script>

<style scoped>
  table tr td {
    word-break: break-all;
  }

  .pager li {
    padding-left: 10px;
  }

  .pager li > a {
    display: inline-block;
    padding: 7px 13px;
    background-color: #3c3834;
    border: 1px solid #6db33f;
    border-radius: 0;
    color: #ffffff;
  }

  .pager li > a:hover {
    background-color: #6db33f;
  }

  .pager li > a.active {
    display: inline-block;
    padding: 7px 13px;
    background-color: #ffffff;
    border: 2px solid #6db33f;
    color: #3c3834;
  }

  .modal {
    overflow-x: hidden;
    overflow-y: auto;
  }

  .modal-dialog {
    z-index: 1045;
  }
</style>
