# Typing Pet Android

휴대폰에서 GitHub Actions로 APK를 빌드할 수 있도록 만든 프로젝트입니다.

## 현재 구현
- 원본에서 추출한 기본/왼손/오른손 이미지 내장
- 다른 앱 위에 펫을 표시하는 오버레이
- 펫 드래그 이동
- Typing Pet 키보드(IME) 등록
- 일반 입력 시 왼손/오른손 이미지 교대
- Enter/Backspace 입력 감지
- 입력 시작 시 기본 포즈

## 휴대폰에서 빌드
1. 이 프로젝트를 GitHub 저장소에 올립니다.
2. Actions 탭 → `Build Typing Pet APK` → `Run workflow`를 누릅니다.
3. 빌드가 끝나면 `TypingPet-debug-apk` artifact에서 APK를 받습니다.

GitHub Actions는 GitHub의 서버에서 워크플로를 실행하며, 빌드 결과 파일을 artifact로 저장할 수 있습니다.
