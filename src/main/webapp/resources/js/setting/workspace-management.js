// 모달 생성 및 설정
var deleteModal = new Modal("삭제 확인","해당 항목을 삭제하시겠습니까?");

var beforeSaveModal = new Modal("저장 확인", "저장하시겠습니까?");

var saveModal = new Modal("저장 완료", "저장이 완료되었습니다.");

var needNameModal = new Modal("카테고리 이름 설정", "카테고리 명을 입력해주세요.");

var needCategoryModal = new Modal("카테고리 선택", "카테고리를 선택해주세요.");

var lackWorkspaceModal = new Modal("워크스페이스 부족", "사용할 수 있는 워크스페이스를 초과하였습니다.");

deleteModal.createTwoButtonModal("삭제", "취소");

deleteModal.makeModalBtn($(".category-delete-button"));

beforeSaveModal.createTwoButtonModal("저장", "취소");

beforeSaveModal.makeModalBtn($(".category-save-button"));

saveModal.createAlertModal();

needNameModal.createAlertModal();

needCategoryModal.createAlertModal();

lackWorkspaceModal.createAlertModal();






