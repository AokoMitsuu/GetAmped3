	� G /efx/missile/homing_missile.mot  �       �X[o�6~N����S1@NY�c�����M�b�IlO�,Q6g�@ҩ���}���,'r��E�A�<$��~�O?ӹ�i(����B�cj%9��r9|���3���d�Ɍ3{L����$�����h^���4}9Ņ K��JQg����K:�ޥ8����.8vJS�}ɲ�l""ɦ�������h�h�F�����rӁ���h]�<�c9־c�ZC2�G0=
LD���t�W0mRH7�cA��a�dpi��i���x�"H�h�� ��J8v������Tn�Ҵ��%LS-�\����N���Ox&#N�+�f+��{6�3NQT��JN�d���?ۑt�j���=���폙�wF1���:Cr
p���`H�pv�#lA��q,���ܨI�~�to�һ�J���@tټ�B�1MO��y;6*U-ř/ɼ,���L�A^F�@�(�b�*XKa���%g�$h2����TEέ���&Vn�,�?�<�MЌlEuLT���F
�ȆqX�(�$��&��D��$��S���/��/�y��@�QE�}}�%�k�U�ڻk^e,$Ђ����r>���3�'��E�x����Jr_��ٴ����Q��ʀ˅�+8���rKF��H�}�����n��3~E=c��{K�_4��+$T��FK��l�V��*lj��hK�"�x�g�R�Pa����9X+����2 3�4�����A%���m�H
h�-��-yU:^��gql��D�}�iY��)X��8E�2n�=A�=�X9Kb�0iv�};,2a㯤�".l�5������P��4Lu��ոa�����&��b����E��a�[_�XEfo ��ì��s��,����e`b{��f����-ao��8��k��M��eL}��Z:~����ل%���4��E���tZ����8n�M�=ep��Lg˙N�F�i`�7�Q�d�wr_5�-�a�&���dԆ��p�-G�8�?�C�71�^�*��U&d���l���E^Y�gwk���Ҫ2&5�R���ր�
�"/�YL0����' ����{�/��5��mԶ7���e��{�	0����ř����	�U�`].r�]~|�ƻ��Ӊwqv����ǳ����� G�R�;����a���/]��.��4��1�ۼ��-B�ޖ�1N���4/�a짇��
b��t��K�XG^����p�����o�vA��f�Ѫؕ!�PR9���2�Mu�lY��$�񰪣�����[��tZk
ws�]��أTi�6� 1���Sk���"O	��rL�#?��Cզ�Ral���w��&n|��^���cͦ����x#���X4r)�+�<���'�Z���Yl�A�[�qw�ŷ�� ��5�m�X��Q�_�(�B�5� ��=Riq��%��N��w�zz"�PN;��:8��(��%�D&��Q���S�\2����ē7�qX��
|l�:�=HC;�x�%4�i�WC�?�|$Q� t{�M�籡�c���&�����%O��{��'D�H�����㣂'D�%D�/���   �  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; smokeD /efx/smoke/smoke_muzzle6.ptc�        ����?L��?L��?L��    B�        rippleD /efx/ripple/ripple01_yel.scm�        ?   @�  @�  @�                targetD /efx/target/target_missile.scm�    <���    ?�  ?�  ?�               Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar4.agi��f� mix2��� /efx/mix/bar2.agi�f� genEfx Lkotori/gfx/KGLScriptModel; W(Lkotori/data/KGLGenerationData;FFFFFFFZILjava/lang/String;)Lkotori/gfx/KGLScriptModel; Y(Lkotori/data/KGLGenerationData;FFFFFFFFFZILjava/lang/String;)Lkotori/gfx/KGLScriptModel; start hit hitstage timeout g pos (FFF)V
 { | } hpb
 {  } sx	 { �  sy	 { �  sz	 { �  connect	 { �  parts	 { �  generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � setShadowState
 d � 3 setEdge (Z)V
 f � �
 H 0 3 finishAllConnections
 d � 1 clearGenerate
 d � 1 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/smoke/curve_smoke.scm�        >�33?�  ?�  ?L��C4          D /accs/transform_gun/efx.scm�        >���?�  ?�  ?�      B�       boostD /efx/tornado/wind_ver6.scm�            ?�  ?�  ?�               roll (Lkotori/geom/Vector3D;I)V
 f � �-        C�   Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 rndc (F)F
 H � � scale (F)V
 { � �>���?�  ?L�� rndf
 H � ��   B�  C�  ´  >L�� 	start_ore ()Lkotori/geom/Vector3D;
 H  � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � t kill
 � � 1 startScript (Ljava/lang/String;)Z
 � � � efx_endD /item/bigbomb/rays.scm�            ?�  ?�  ?�                	fadeColor (FFFFI)V
 d � � 	playSound (IIFF)V
 d � �?��� attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            � G� � hit_transform_gun_bj(  d� ���  �     1 � explode
 d � 1
 d � 3?�ffD /efx/step/smoke_lan9_red.ptc�            ?�  ?�  ?�               setBillboard
 � � � setBillboardPitch
 � � � setBillboardBank
 � � �Bp  D /efx/smoke/smoke_black3.ptc�            ?�  ?�  ?�              @   D /efx/smoke/smoke_black3.ptc�            ?�  ?�  ?�              D /efx/explode/exp_sphere1.scm�            ?��?��?��             start13 
setVisible
 f � �
 f � 1-            � G� �(  d� ��   �     1 �?333>���D /efx/step/smoke_lan9_red.ptc�            ?�  ?�  ?�              D /efx/smoke/smoke_black3.ptc�            ?�  ?�  ?�              D /efx/smoke/smoke_black3.ptc�            ?�  ?�  ?�              D /efx/explode/exp_sphere1.scm�            ?��?��?��             f h g j g l g o n q n - 1  i� k�m�p�r��  s u  *+$%	
� �  s v  :+$%� ~W+� �W+� �+	� �+
� �+� �+� �*+� ��  w 1  �*� �W*� �W*� �W*� �W*� �W**�� �� �W**��� �� �W�L*� �W*�� �W*d� �W=� g* ȶ �Wp� .� � �� ��b� �W� � �� ��b� �W*� � �W*+���� ���� �b�� W*<� �W�W����  x 1 �*� �� �W*� �� *� �� �W*� �� *� �Ŷ �W*�w� �L*bd� �W*4Ͷ �W*,� �W+� +� �W*�Ҷ �W*� �W*!� �W*!ֶ �W�M*,�b� �b�jw� N-� -� �W-�� �W*,�b� �b�jw� N-� -� �W-�� �W-޶ �W*,���b� �b�jw� N-� 	-� �W*��� ��� ��� ��b� �bw� W*��� ��� ��� ��b� �bw� W*�� �W*2� �W*� �W*2� �W* ȶ �W*� �W�  y 1  *� W�  z 1 (*� �W*�� �W*� �W*!� �W*!� �W�L*+b� �b�jw� M,� ,� �W,�� �W*+b� �b�jw� M,� ,� �W,�� �W,޶ �W*+��b� �b�jw� M,� 	,� �W*��� ��� ��� ��b� �bw� W*��� ��� ��� ��b� �bw� W*�� �W*2� �W*� �W*2� �W* ȶ �W*� �W� gv�c        ��� >��>��>����  